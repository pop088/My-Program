import os
import database
import urllib
from datetime import datetime

from google.appengine.api import users
from google.appengine.api import images
from google.appengine.ext import ndb
from google.appengine.ext import blobstore
from google.appengine.ext.webapp import blobstore_handlers

import jinja2
import webapp2

JINJA_ENVIRONMENT = jinja2.Environment(
    loader=jinja2.FileSystemLoader(os.path.dirname(__file__)),
    extensions=['jinja2.ext.autoescape'],
    autoescape=True)

class SingleStreamMain(webapp2.RequestHandler):
    def get(self):

        try:
            user = users.get_current_user().user_id()
        except:
            return self.redirect("/")

        user = users.get_current_user().user_id()
        if user:
            url = users.create_logout_url(self.request.uri)
            url_linktext = 'Logout'
        else:
            url = users.create_login_url(self.request.uri)
            url_linktext = 'Login'

        stream_name = self.request.get('stream_name')
        more=self.request.get('more')
        user = users.get_current_user().user_id()

        streaming = database.stream.query().fetch()

        exist = database.stream.query(database.stream.stream_id==stream_name).fetch()

        if not exist:
            return self.redirect("/error2")

        own=0
        for mystream in streaming:
            if mystream.stream_id==stream_name and mystream.user_id!=user:
                mystream.numview = mystream.numview+1
                mystream.view.append(datetime.now())
                own=1
                mystream.put()
                break

        allimage_query = database.imagedata.query(ndb.AND(
            database.imagedata.stream_id == stream_name,database.imagedata.date!=None)).order(database.imagedata.date)

        if more=='1':
            allimage = allimage_query.fetch()
        else:
            allimage=allimage_query.fetch(3)

        upload_url = blobstore.create_upload_url('/upload_photo')

        template_values = {
            'more':more,
            'allimage': allimage,
            'stream_name': urllib.quote_plus(stream_name),
            'upload_url':upload_url,
            'url': url,
            'url_linktext': url_linktext,
        }

        if own==1:
            template = JINJA_ENVIRONMENT.get_template('Single_stream.html')
            self.response.write(template.render(template_values))
        elif own==0:
            template = JINJA_ENVIRONMENT.get_template('Single_stream_owner.html')
            self.response.write(template.render(template_values))


class SingleStream(blobstore_handlers.BlobstoreUploadHandler):
    def post(self):
            stream_name = self.request.get('stream')

            user = users.get_current_user().user_id()
            streaming = database.stream.query(
            ancestor = ndb.Key('User', user)).fetch()

            upload = self.get_uploads()[0]
            user_photo = database.imagedata(parent=ndb.Key('User', user)) #everyone can upload
            user_photo.blob_key =upload.key()
            user_photo.comment = self.request.get('comment')
            user_photo.name = self.request.get('name')
            user_photo.stream_id = stream_name
            user_photo.url = images.get_serving_url(upload.key())
            user_photo.user_id = user

            for mystream in streaming:
                if mystream.stream_id==stream_name:
                    if(mystream.cover_key==None):
                        mystream.cover_key=upload.key()
                        mystream.cover_url =images.get_serving_url(upload.key())


                    mystream.blob_key.append(upload.key())
                    user_photo.put()
                    mystream.numberofpic = mystream.numberofpic + 1
                    last_add = str(datetime.now())
                    mystream.last_add=last_add[:19]
                    mystream.put()
                    break



            direct = {'stream_name': self.request.get('stream')}
            direct = urllib.urlencode(direct)
            self.redirect('/singlestream?' + direct)


class subscribe(webapp2.RequestHandler):
    def post(self):
        user = users.get_current_user().user_id()
        stream_name = self.request.get('stream_name')

        sub = database.Subscribe()
        sub.stream_id=stream_name
        sub.user_id=user
        sub.put()

        direct = {'stream_name': self.request.get('stream_name')}
        direct = urllib.urlencode(direct)

        self.redirect('/singlestream?' + direct)


class ViewPhotoHandler(blobstore_handlers.BlobstoreDownloadHandler):
    def get(self):
        blob = self.request.get('blob')
        self.send_blob(blob)

class More(webapp2.RequestHandler):
    def post(self):
            direct = {'stream_name': self.request.get('stream_name')}
            direct = urllib.urlencode(direct)
            direct2 = {'more': 1}
            direct2 = urllib.urlencode(direct2)
            self.redirect('/singlestream?' + direct+'&'+direct2)

app = webapp2.WSGIApplication([
    ('/singlestream', SingleStreamMain),
    ('/view_photo',ViewPhotoHandler),
    ('/upload_photo', SingleStream),
    ('/subscribe',subscribe),
    ('/more',More)
        ], debug=True)
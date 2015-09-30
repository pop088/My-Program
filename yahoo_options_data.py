from bs4 import BeautifulSoup
import cgi
import re
import json
def contractAsJson(filename):

    soup = BeautifulSoup(open(filename), "html.parser")
    data = soup.find_all("span",class_="time_rtq_ticker")
    for element in data:
        pprice=element.get_text()
    price= float(pprice)
# scrpe the price
    url=[]
    data = soup.find_all("tr",valign="top")
    for elements in data[0]:
        ahref=elements.find_all("a")
        for element in ahref:
            k=unicodeToHTMLEntities(element["href"])
            url.append (k)

    dateurls=[]
    for i in url:
        if ("000"not in i and len(i)<30):
            i="http://finance.yahoo.com"+i
            dateurls.append(i)
# scrpe the dateurls
    select=[]
    for i in url:
        if ("/q?s" in i and len(i)<40):
            select.append(i)

    symbol=[]
    date=[]
    type=[]
    for i in select:
        k = re.search( r'\d', i, )
        index=0
        while(index<len(i)):
            if(i[index]==k.group()):
                if i[index]=='7':
                    j1=i[5:(index+1)]
                    j2=i[(index+1):(index+7)]
                    j3=i[index+7]
                else:
                    j1=i[5:index]
                    j2=i[index:(index+6)]
                    j3=i[index+6]
                symbol.append(j1)
                date.append(j2)
                type.append(j3)
                break
            index=index+1

    strike=[]
    for elements in data[0]:
        strikedata= elements.find_all("strong")
        for g in strikedata:
            if("."in g.get_text()and len(g.get_text())<10):
                strike.append(g.get_text())

    last=[]
    change=[]
    bid=[]
    ask=[]
    vol=[]
    oopen=[]
    flag=1
    for elements in data[0]:
        numberdata= elements.find_all("td" ,align="right" )
        for g in numberdata:
            if len(g.get_text())<10 and flag%6==1:
                last.append(g.get_text())
                flag=flag+1
            elif len(g.get_text())<10 and flag%6==2:
                change.append(g.get_text())
                flag=flag+1
            elif len(g.get_text())<10 and flag%6==3:
                bid.append(g.get_text())
                flag=flag+1
            elif len(g.get_text())<10 and flag%6==4:
                ask.append(g.get_text())
                flag=flag+1
            elif len(g.get_text())<10 and flag%6==5:
                vol.append(g.get_text())
                flag=flag+1
            elif len(g.get_text())<10 and flag%6==0:
                oopen.append(g.get_text())
                flag=flag+1

    name=("Ask","Bid","Change","Date","Last","Open","Strike","Symbol","Type","Vol")
    d=dict(zip(name,range(10)))
    Quotes=[]
    for i in range(len(strike)):
        d=dict(zip(name,range(10)))
        d["Ask"]=ask[i]
        d["Bid"]=bid[i]
        d["Change"]=change[i]
        d["Date"]=date[i]
        d["Last"]=last[i]
        d["Open"]=oopen[i]
        d["Strike"]=strike[i]
        d["Symbol"]=symbol[i]
        d["Type"]=type[i]
        d["Vol"]=vol[i]
        Quotes.append(d)

    QQ = sorted(Quotes,key=lambda x:convert_no_comma(x["Open"]),reverse=1)

    wholelist={"optionQuotes":QQ,"dateUrls":dateurls,"currPrice":price}

    jsonQuoteData= json.dumps(wholelist, sort_keys=True,indent=4, separators=(',', ': '))

    return jsonQuoteData

def unicodeToHTMLEntities(text):
    text = cgi.escape(text).encode('ascii', 'xmlcharrefreplace')
    return text

def convert_no_comma(a):
    c=str(a)
    b=re.sub("[\,]", "",c)
    return int(b)

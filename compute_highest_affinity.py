def highest_affinity(site_list, user_list, time_list):

    n=len(site_list)
    temp_site_list=[]
    new_site_list=[]
    sssite_list= list(site_list)
    for i in sssite_list:
        if(i not in temp_site_list):
            temp_site_list.append(i)
            sssite_list.remove(i)

    for i in sssite_list:
        if(i not in new_site_list):
            new_site_list.append(i)

    new_site_list.sort()
    sn = len(new_site_list)

#Select and sort site list, rule out the random website and repetition

    new_user_list=[]
    for i in user_list:
        if(i not in new_user_list):
            new_user_list.append(i)

    new_user_list.sort()
    un = len(new_user_list)

#Select and sort user list, rule out the repetition

    site_browse=[]
    for i in new_user_list:
        p=[]
        for j in range(n):
            if(user_list[j]==i and site_list[j] not in p and site_list[j] in new_site_list):
                p.append(site_list[j])
        p_tuple=tuple(p)
        site_browse.append(p_tuple)

    ul = tuple(new_user_list)

    log=dict(zip(ul,site_browse))

#build a dictionary where key is the users and the value are the website that they have browse.

    aff=[]
    for i in range(sn):
        for j in range(i+1,sn):
            aff.append(new_site_list[i]+" "+new_site_list[j])

    aff_tuple=tuple(aff)
    number= [0]*len(aff_tuple)
    affinity=dict(zip(aff_tuple,number))

#build a dictionary where key is the combination of every two sites in the list and the value is the affinity

    for i in new_user_list:
        for j in range(len(log[i])):
            for k in range(j+1,len(log[i])):
                if((log[i][j]+" "+log[i][k]) in aff):
                    affinity[(log[i][j]+" "+log[i][k])]=affinity[(log[i][j]+" "+log[i][k])]+1

#Traverse the log and caculate the affinity of every combination of sites

    raw = max(affinity, key=lambda key: affinity[key])
    output=raw.split()
    result=tuple(output)
    return result







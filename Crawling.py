from selenium import webdriver
from bs4 import BeautifulSoup
import time
import pymongo

driver = webdriver.Chrome('C:\pythonlib\chromedriver_win32\chromedriver.exe')
connection = pymongo.MongoClient('localhost', 27017)
db=connection.restaurant

def Crawling(place, type, subject):
    if place == '을지로':
        collection = db.euljiro
    elif place == '명동':
        collection = db.myeongdong
    elif place == '건대':
        collection = db.geondae
    elif place == '홍대':
        collection = db.hongdae
    elif place =='강남':
        collection = db.gangnam

    for i in range(1):
        driver.get("https://map.naver.com/v5/search/"+place+''+type+''+subject)
        time.sleep(5)
        source = driver.page_source
        bs = BeautifulSoup(source,'lxml')
        entire = bs.find('div',class_='list_search')
        li_lists = entire.find_all('search-item-place')
        count = 1

        for infor in li_lists:
            if infor.find('div', class_='link_search'):
                infor_img = infor.find('a', class_='thumb_box ng-star-inserted')
                if infor_img is None:
                    infor_img = 'img is null'
                else:
                    infor_img = infor_img.find('img')
                    infor_img = infor_img.get('src')

                infor_name = infor.find('div', class_='search_box')
                infor_name = infor_name.find('span', class_='search_title_text')
                infor_name = infor_name.get_text()

                infor_cate = infor.find('div', class_='search_text_box')
                infor_cate = infor_cate.find('span', class_='search_text category ng-star-inserted')
                if infor_cate is None:
                    infor_cate = 'Category is null'
                else:
                    infor_cate = infor_cate.get_text()

                infor_tel = infor.find('div', class_='search_text_box')
                infor_tel = infor_tel.find('span', class_='search_text phone ng-star-inserted')
                if infor_tel is None:
                    infor_tel = 'Tel is null'
                else:
                    infor_tel = infor_tel.get_text()

                infor_addr = infor.find('div', class_='search_text_box ng-star-inserted')
                if infor_addr is None:
                    infor_addr = 'address is null'
                else:
                    infor_addr = infor_addr.find('span', class_='search_text address')
                    infor_addr = infor_addr.get_text()

                driver.find_element_by_xpath("/html/body/app/layout/div/div[2]/div[2]/shrinkable-layout/search-layout/search-list/search-list-contents/perfect-scrollbar/div/div[1]/div/div/div/search-item-place["+str(count)+"]/div/div[2]/div[2]/a").click()
                count+=1
                time.sleep(1)
                infor_review = driver.page_source
                bs2 = BeautifulSoup(infor_review, 'html.parser')
                infor_review = bs2.find('div', class_='title_area')
                infor_review = infor_review.find('span', class_='title_count')
                infor_review = infor_review.get_text()
                driver.back()
                time.sleep(1)

                shop_info = {'img': infor_img,
                             'name': infor_name,
                             'category': infor_cate,
                             'tel': infor_tel,
                             'addr': infor_addr,
                             'type': type,
                             'review': infor_review}

                print(shop_info)
                collection.save(shop_info)

place = ['홍대','건대','명동','강남','을지로' ]
type = ['중식', '한식','일식', '양식']

for i in range(1,5):
    for j in range(1,4):
        Crawling(place[i], type[j], '맛집')

print("------finish------")


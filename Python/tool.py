# -*- coding: UTF-8 -*-
import re
#处理获得的宝贝页面
class Tool:

    #初始化
    def __init__(self):
        pass

    #获得页码数
    def getPageNum(self,page):
        pattern = re.compile(u'<div class="total">.*?\u5171(.*?)\u9875',re.S)
        result = re.search(pattern,page)
        if result:
            print "找到了共多少页"
            pageNum = result.group(1).strip()
            print '共',pageNum,'页'
            return pageNum

    def getGoodsInfo(self,page):
        #u'\u8ba2\u5355\u53f7'是订单号的编码
        pattern = re.compile(u'dealtime.*?>(.*?)</span>.*?\u8ba2\u5355\u53f7.*?<em>(.*?)</em>.*?shopname.*?title="(.*?)".*?baobei-name">.*?<a.*?>(.*?)</a>.*?'
                             u'price.*?title="(.*?)".*?quantity.*?title="(.*?)".*?amount.*?em.*?>(.*?)</em>.*?trade-status.*?<a.*?>(.*?)</a>',re.S)
        result = re.findall(pattern,page)
        for item in result:
            print '------------------------------------------------------------'
            print "购买日期:",item[0].strip(), '订单号:',item[1].strip(),'卖家店铺:',item[2].strip()
            print '宝贝名称:',item[3].strip()
            print '原价:',item[4].strip(),'购买数量:',item[5].strip(),'实际支付:',item[6].strip(),'交易状态',item[7].strip() 

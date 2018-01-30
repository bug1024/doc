
function getOrderIds() {
    var url = "https://tradearchive.taobao.com/trade/detail/trade_item_detail.htm?bizOrderId=";
    var list = document.getElementsByClassName("bought-wrapper-mod__trade-order___2lrzV");
    var orderIds = new Array();
    for (i in list) {
        var value = list[i];
        var orderId = "";
        if (value instanceof HTMLElement) {
            orderId = value.getAttribute("data-id");
        }
        if (orderId != undefined && orderId != "") {
            orderIds.push(url + orderId);
        }
    }

    return orderIds;
}

var orderIds = getOrderIds();

// 订单号
var orderNum = document.getElementsByClassName("order-num")[0].innerText;
// 运费单号
var expressNumber = document.getElementsByClassName("simple-list")[0].rows[5].cells[1].innerText;


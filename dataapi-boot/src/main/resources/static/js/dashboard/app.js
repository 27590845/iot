function delDashboard(dashboardName) {
    deleteHttp({
        url: '/grafana/'+dashboardName
    }).then(res => {
        alert("可视化页面删除成功，关掉弹窗后退出");
        window.opener = null;
        window.close()
    }).catch(_=>{
        alert("可视化页面删除失败，请稍后再试");
    });
}

window.onload = function() {
    let timer = setInterval(function () {
        let dashboardName = $(".navbar-page-btn").find("a").html();
        if(dashboardName != null){
            $(".sidemenu").remove();
            $(".navbar-page-btn").find('a').remove();
            $(".navbar-page-btn").find('div').after('<button onclick="delDashboard(\''+dashboardName+'\')" style="margin-left: 20px">删除</button>');
            $(".navbar-page-btn").find('div').after('<span>'+dashboardName+'</span>');
        }
    }, 100);
    setTimeout(function () {
        clearInterval(timer);
        timer = null;
    }, 5000);
}
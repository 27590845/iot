function getQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    let r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]);
    return null;
}

/***   构建树形下拉多选框数据 start   ***/
function buildTreeData(nodes) {
    let nodesData = [];
    for (let i=0; i<nodes.length; i++) {
        let node = nodes[i];
        let attrs = node.nodeAttrList;
        let attrsData = [];
        for(let j=0; j<attrs.length; j++){
            let attr = attrs[j];
            attrsData.push({text: attr.naKey, nodeSn: node.nodeSn, type: "attr"});
        }
        nodesData.push({text: node.nodeSn, type: "node", nodes: attrsData});
    }
    return nodesData;
}

const selectedItems = new Set();
let sceneSn = "";

/**
 * 点击attr后，把attr添加到已选集合中，并展示出来，如果attr已存在集合中，就删除该attr，并取消展示
 * @param treeItem
 */
function onItemClick(treeItem){
    // 只能添加attr
    if(treeItem.type != "attr") return;
    //newItem为attr的唯一标记，格式为nodeSn_attrKey
    let newItem = treeItem.nodeSn + "_" + treeItem.text;
    if(selectedItems.has(newItem)){
        selectedItems.delete(newItem);
        $("#"+newItem).remove();
    }else {
        let html = `
        <div id="${newItem}" style="float: left; width: auto; height: auto; margin: 5px; padding: 10px; border-radius: 5px; background: lightskyblue; color: black">
            ${newItem}
        </div>`;
        if(selectedItems.size % 6 == 0) html+="<br>";
        selectedItems.add(newItem);
        $("#selected_items").append(html);
    }
}
/***   构建树形下拉多选框数据 end   ***/

function submit(){
    let baseInfos = [];
    selectedItems.forEach(function (item) {
        let info = item.split("_");
        baseInfos.push({sceneSn: sceneSn, nodeSn: info[0], attrKey: info[1]})
    });
    postHttp({
        url: '/grafana/'+sceneSn,
        data: JSON.stringify(baseInfos)
    }).then(res => {
        promptModel({
            type: "modal-header-success",
            title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>创建可视化页面',
            body: '创建可视化页面完成'
        })
        setTimeout(function() {
            window.opener = null;
            window.close()
        }, 3000)
    }).catch(_=>{
        promptModel({
            type: "modal-header-danger",
            title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>创建可视化页面',
            body: '可视化页面创建失败，请稍后再试!'
        })
    });
}

$(function () {
    getHttp({
        url: "/scene/"+getQueryString("sceneSn"),
        contentType: "application/json"
    }).then(res => {
        sceneSn = res.sceneSn;
        let treeData = buildTreeData(res.nodeVos);
        $('#treeView').treeview({
            data: treeData,
            levels: 2,
            backColor: '#cccccc'
        });
        $('#treeView').on('nodeSelected', function(event, data) {
            onItemClick(data);
        });
    }).catch(res => {
        promptModel({
            type: "modal-header-danger",
            title: '<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>网关信息',
            body: '网关信息获取失败'
        })
    })
})

$(function(){
    // 侧边导航栏中样式的选择和点击效果【通用组件的功能，在base.js中进行定义，方便维护】
    let navinfo = {
        id: "page-nav",
        title: "页面导航",
        content: {
            'index.html': "数据中心",
            'scenelist.html': "场景列表"
        },
        type: 'scene',
        tag: "glyphicon glyphicon-tags"
    }
    broadside(navinfo, location)

    // 路径导航
    trajectoryUnCode(location.pathname, location.search)

    // 进行网络请求

})
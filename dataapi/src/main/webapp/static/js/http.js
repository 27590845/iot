var baseurl = location.origin
/**
 * 发送GET网络请求
 * @param {Object} request 发送请求的内容，包含至少包含url
 */
function getHttp(request) {
	// let request = {
	//  type: "请求方式",
	//  data: "传递的参数",
	// 	url: "请求的地址",
	// 	contentType: "数据的类型",
	// 	dataType: "返回后的数据格式"
	// }
	return new Promise(function(resolve, reject) {
		$.ajax({
			type: "GET",
			url: baseurl + request.url,
			contentType: request.contentType || "application/json",
			dataType: request.dataType || "json",
			success: function(res) {
				res.success ? resolve(res.data) : reject(res)
			},
			error: function(res) {
				reject(res)
			}
		})
	})
}

/**
 * 发送PUT网络请求
 * @param {Object} request 发送请求的内容，包含至少包含url、data
 */
function putHttp(request) {
	// let request = {
	//  type: "请求方式",
	//  data: "传递的参数",
	// 	url: "请求的地址",
	// 	contentType: "数据的类型",
	// 	dataType: "返回后的数据格式"
	// }
	return new Promise(function(resolve, reject) {
		$.ajax({
			type: "PUT",
			url: baseurl + request.url,
			data: request.data || {},
			contentType: request.contentType || "application/json",
			dataType: request.dataType || "json",
			success: function(res) {
				res.success ? resolve(res) : reject(res)
			},
			error: function(res) {
				reject(res)
			}
		})
	})
}

/**
 * 发送POST网络请求
 * @param {Object} request 发送请求的内容，包含至少包含url、data
 */
function postHttp(request) {
	// let request = {
	//  type: "请求方式",
	//  data: "传递的参数",
	// 	url: "请求的地址",
	// 	contentType: "数据的类型",
	// 	dataType: "返回后的数据格式"
	// }
	return new Promise(function(resolve, reject) {
		$.ajax({
			type: "POST",
			url: baseurl + request.url,
			data: request.data || {},
			contentType: request.contentType || "application/json",
			dataType: request.dataType || "json",
			success: function(res) {
				res.success ? resolve(res) : reject(res)
			},
			error: function(res) {
				reject(res)
			}
		})
	})
}

/**
 * 发送DELETE网络请求
 * @param {Object} request 发送请求的内容，包含至少包含url、data
 */
function deleteHttp(request) {
	// let request = {
	//  type: "请求方式",
	//  data: "传递的参数",
	// 	url: "请求的地址",
	// 	contentType: "数据的类型",
	// 	dataType: "返回后的数据格式"
	// }
	return new Promise(function(resolve, reject) {
		$.ajax({
			type: "DELETE",
			url: baseurl + request.url,
			contentType: request.contentType || "application/json",
			dataType: request.dataType || "json",
			success: function(res) {
				res.success ? resolve(res) : reject(res)
			},
			error: function(res) {
				reject(res)
			}
		})
	})
}

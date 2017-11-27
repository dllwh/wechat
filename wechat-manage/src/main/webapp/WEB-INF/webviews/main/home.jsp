<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head  lang="en">
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>

<title>${_currProject}</title>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
	<script src="${_currConText }/plug-in/echarts2/echarts.js"></script>
	<script src="${_currConText }/plug-in/assets/js/bootstrap.min.js"></script>
	<title>${_currProject}</title>
	<script type="text/javascript" src="${_currConText }/plug-in/tools/ExtJavascript.js"></script>
</head>
<body>
	<div class="page-content clearfix">
		<div class="alert alert-block alert-success">
			<button type="button" class="close" data-dismiss="alert">
				<i class="icon-remove"></i>
			</button>
			<login:getUserIp var="userIp"/>
			<login:getCurrentTime var="currentTime" pattern="yyyy年MM月dd日  HH时mm分ss秒"/>
			<i class="icon-ok green"></i>
			欢迎使用<strong class="green">后台管理系统<small>(v1.0)</small></strong>,
			你本次登陆时间为${currentTime }，登陆IP:${userIp }	
		</div>
		<div class="state-overview clearfix">
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol terques">
						<i class="icon-user"></i>
					</div>
					<div class="value">
						<h1>34522</h1>
						<p>网站会员</p>
					</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol red">
						<i class="icon-bar-chart"></i>
					</div>
					<div class="value">
						<h1>34522</h1>
						<p>登录记录</p>
					</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol blue">
						<i class="icon-bar-chart"></i>
					</div>
					<div class="value">
						<h1>34522</h1>
						<p>操作记录</p>
					</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol blue">
						<i class="icon-comment"></i>
					</div>
					<div class="value">
						<h1>1349</h1>
						<p>新信息</p>
					</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol blue">
						<i class="icon-mobile-phone"></i>
					</div>
					<div class="value">
						<h1>653</h1>
						<p>苹果设备</p>
					</div>
				</section>
			</div>
			<div class="col-lg-3 col-sm-6">
				<section class="panel">
					<div class="symbol blue">
						<i class="icon-mobile-phone"></i>
					</div>
					<div class="value">
						<h1>786</h1>
						<p>安卓设备</p>
					</div>
				</section>
			</div>
		</div>
		
		<%-- 实时交易记录 --%>
		<div class="clearfix">
			<div class="t_Record">
				<div id="main" style="height:300px; overflow:hidden;overflow:auto" ></div> 
			</div>
			<div class="news_style">
				<div class="title_name">最新消息</div>
				<ul class="list">
					<li>
						<i class="icon-bell red"></i><a>后台管理什么时候能用啊！</a>
					</li>
					<li>
						<i class="icon-bolt"></i><a>后台管理什么时候能用啊！</a>
					</li>
					<li>
						<i class="icon-bullhorn"></i><a>后台管理什么时候能用啊！</a>
					</li>
					<li>
						<i class="icon-comment"></i><a>后台管理什么时候能用啊！</a>
					</li>
					<li>
						<i class="icon-plus"></i><a>后台管理什么时候能用啊！</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$(".t_Record").width($(window).width()-320);
	//当文档窗口发生改变时 触发  
	$(window).resize(function(){
		$(".t_Record").width($(window).width()-320);
	});
});

	require.config({
		paths : {
			echarts : '${_currConText }/plug-in/echarts2'
		}
	});
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/line', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
	'echarts/chart/bar' ], function(ec, theme) {
        var myChart = ec.init(document.getElementById('main'),theme);
       option = {
			title : {
				text : '月用户登录记录',
				subtext : '实时获取用户登录记录'
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : [ '所有记录', '成功记录', '失败记录' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : true
					},
					dataView : {
						show : true,
						readOnly : false
					},
					magicType : {
						show : true,
						type : [ 'line', 'bar' ]
					},
					restore : {
						show : true
					},
					saveAsImage : {
						show : true
					}
				}
			},
			calculable : true,
			xAxis : [ {
				type : 'category',
				data : [ '1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月','10月', '11月', '12月' ]
			} ],
			yAxis : [ {
				type : 'value'
			} ],
			series : [
					{
						name : '所有记录',
						type : 'bar',
						data : [ 120, 49, 70, 232, 256, 767, 1356, 1622, 326,200, 164, 133 ],
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							} ]
						}
					},
					{
						name : '成功记录',
						type : 'bar',
						data : [ 26, 59, 30, 84, 27, 77, 176, 1182, 487, 188,
								60, 23 ],
						markPoint : {
							data : [ {
								name : '年最高',
								value : 1182,
								xAxis : 7,
								yAxis : 1182,
								symbolSize : 18
							}, {
								name : '年最低',
								value : 23,
								xAxis : 11,
								yAxis : 3
							} ]
						},

					},
					{
						name : '失败记录',
						type : 'bar',
						data : [ 26, 59, 60, 264, 287, 77, 176, 122, 247, 148,
								60, 23 ],
						markPoint : {
							data : [ {
								name : '年最高',
								value : 172,
								xAxis : 7,
								yAxis : 172,
								symbolSize : 18
							}, {
								name : '年最低',
								value : 23,
								xAxis : 11,
								yAxis : 3
							} ]
						},

					} ]
		};

		myChart.setOption(option);
	});
</script>
</html>


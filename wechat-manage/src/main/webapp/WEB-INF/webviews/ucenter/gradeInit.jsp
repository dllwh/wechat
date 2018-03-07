<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>${_currProject}--会员等级</title>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/ace.jsp"%>
</head>
<body>
	<div class="grading_style">
		<div id="category">
			<div id="scrollsidebar" class="left_Treeview">
				<div class="show_btn" id="rightArrow">
					<span></span>
				</div>
				<div class="widget-box side_content">
					<div class="side_title">
						<a title="隐藏" class="close_btn"><span></span></a>
					</div>
					<div class="side_list">
						<div class="widget-header header-color-green2">
							<h4 class="lighter smaller">会员等级</h4>
						</div>
						<div class="widget-body">
							<ul class="b_P_Sort_list">
								<li>
									<i class="orange  fa fa-user-secret"></i>
									<a>全部(235)</a></li>
								<li>
									<i class="fa fa-diamond pink"></i>
									<a>普通会员(235)</a>
								</li>
								<li>
									<i class="fa fa-diamond pink"></i>
									<a>铁牌会员(2215)</a>
								</li>
								<li>
									<i class="fa fa-diamond pink"></i>
									<a>铜牌会员(3456)</a></li>
								<li>
									<i class="fa fa-diamond pink"></i>
									<a>银牌会员(4332)</a>
								</li>
								<li>
									<i class="fa fa-diamond pink"></i>
									<a>金牌会员(1332)</a>
								</li>
								<li>
									<i class="fa fa-diamond grey"></i>
									<a>钻石会员(4543)</a>
								</li>
								<li>
									<i class="fa fa-diamond red"></i>
									<a>红钻会员(343)</a>
								</li>
								<li>
									<i class="fa fa-diamond blue"></i>
									<a>蓝钻会员(2343)</a>
								</li>
								<li>
									<i class="fa fa-diamond grey"></i>
									<a>黑钻2(53)</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!--右侧样式-->
			<div class="page_right_style right_grading">
				<div class="Statistics_style" id="Statistic_pie">
					<div class="type_title">
						等级统计 
						<span class="top_show_btn Statistic_btn">显示</span>
						<span class="Statistic_title Statistic_btn">
							<a title="隐藏" class="top_close_btn">隐藏</a>
						</span>
					</div>
					<div id="Statistics" class="Statistics"></div>
				</div>
				<!--列表样式-->
				<div class="grading_list">
					<div class="type_title">全部会员等级列表</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<script type="text/javascript"> 
	$(function() { 
		// 左侧菜单收缩、展开控制
		$("#category").fix({
			float : 'left',
			// minStatue : true,
			skin : 'green',	
			durationTime :false,
			spacingw:20,
			spacingh:240,
			set_scrollsidebar:'.right_grading',
		});

		// 统计区域的显示、隐藏控制
		$("#Statistic_pie").fix({
			float : 'top',
			//minStatue : true,
			skin : 'green',	
			durationTime :false,
			spacingw:0,
			spacingh:0,
			close_btn:'.top_close_btn',
			show_btn:'.top_show_btn',
			side_list:'.Statistics',
			close_btn_width:80,
			side_title:'.Statistic_title',
		});
	});

	//初始化宽度、高度  
	$(".widget-box").height($(window).height());
	$(".page_right_style").width($(window).width() - 220);
	//$(".table_menu_list").width($(window).width()-240);
	//当文档窗口发生改变时 触发  
	$(window).resize(function() {
		$(".widget-box").height($(window).height());
		$(".page_right_style").width($(window).width() - 220);
		//$(".table_menu_list").width($(window).width()-240);
	})
	/**************/

	require.config({
		paths : {
			echarts : '${_currConText }/plug-in/assets/dist'
		}
	});
	require([ 'echarts', 'echarts/theme/macarons', 'echarts/chart/pie', // 按需加载所需图表，如需动态类型切换功能，别忘了同时加载相应图表
	'echarts/chart/funnel' ], function(ec, theme) {
		var myChart = ec.init(document.getElementById('Statistics'), theme);
		option = {
			title : {
				text : '用户等级统计',
				subtext : '实时更新最新等级',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {

				x : 'center',
				y : 'bottom',
				data : [ '普通用户', '铁牌用户', '铜牌用户', '银牌用户', '金牌用户', '钻石用户',
						'蓝钻用户', '红钻用户' ]
			},
			toolbox : {
				show : true,
				feature : {
					mark : {
						show : false
					},
					dataView : {
						show : false,
						readOnly : true
					},
					magicType : {
						show : true,
						type : [ 'pie', 'funnel' ],
						option : {
							funnel : {
								x : '25%',
								width : '50%',
								funnelAlign : 'left',
								max : 6200
							}
						}
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
			series : [ {
				name : '品牌数量',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [ {
					value : 1200,
					name : '普通用户'
				}, {
					value : 1100,
					name : '铁牌用户'
				}, {
					value : 1300,
					name : '铜牌用户'
				}, {
					value : 1000,
					name : '银牌用户'
				}, {
					value : 980,
					name : '金牌用户'
				}, {
					value : 850,
					name : '钻石用户'
				}, {
					value : 550,
					name : '蓝钻用户'
				}, {
					value : 220,
					name : '红钻用户'
				},

				]
			} ]
		};
		myChart.setOption(option);
	});
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<link rel="stylesheet" href="${_currConText }/plug-in/assets/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/animate.css" />
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />

<title>Bootstrap网格 - Bootstrap后台管理系统模版</title>
<style type="text/css">
.center {
	text-align: center;
}

.center [class*="col-"] {
	margin-top: 2px;
	margin-bottom: 2px;
	padding-top: 4px;
	padding-bottom: 4px;
	position: relative;
	text-overflow: ellipsis;
}

.center [class*="col-"]  span {
	position: relative;
	z-index: 2;
	display: inline-block;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 100%;
}

.center [class*="col-"]:before {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 2px;
	right: 2px;
	content: "";
	display: block;
	border: 1px solid #DDD;
	z-index: 1;
}

.center [class*="col-"]:hover:before {
	background-color: #FCE6A6;
	border-color: #EFD27A;
}
</style>
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>栅格设置</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>通过下表可以详细查看 Bootstrap 的栅格系统是如何在多种屏幕设备上工作的。</p>
						<div class="table-responsive">
							<table class="table table-bordered table-striped">
								<thead>
									<tr>
										<th></th>
										<th>超小屏幕 <small>手机 (&lt;768px)</small></th>
										<th>小屏幕<small>平板 (≥768px)</small></th>
										<th>中等屏幕<small>桌面显示器 (≥992px)</small></th>
										<th>大屏幕<small>大桌面显示器 (≥1200px)</small></th>
									</tr>
								</thead>
								</tbody>
								<tbody>
									<tr>
										<th class="text-nowrap">栅格系统行为</th>
										<td>总是水平排列</td>
										<td colspan="3">开始是堆叠在一起的，当大于这些阈值时将变为水平排列C</td>
									</tr>
									<tr>
										<th class="text-nowrap"><code>.container</code> 最大宽度</th>
										<td>None （自动）</td>
										<td>750px</td>
										<td>970px</td>
										<td>1170px</td>
									</tr>
									<tr>
										<th class="text-nowrap">类前缀</th>
										<td><code>.col-xs-</code></td>
										<td><code>.col-sm-</code></td>
										<td><code>.col-md-</code></td>
										<td><code>.col-lg-</code></td>
									</tr>
									<tr>
										<th class="text-nowrap">列（column）数</th>
										<td colspan="4">12</td>
									</tr>
									<tr>
										<th class="text-nowrap">最大列（column）宽</th>
										<td class="text-muted">自动</td>
										<td>~62px</td>
										<td>~81px</td>
										<td>~97px</td>
									</tr>
									<tr>
										<th class="text-nowrap">槽（gutter）宽</th>
										<td colspan="4">30px （每列左右均有 15px）</td>
									</tr>
									<tr>
										<th class="text-nowrap">可嵌套</th>
										<td colspan="4">是</td>
									</tr>
									<tr>
										<th class="text-nowrap">偏移（Offsets）</th>
										<td colspan="4">是</td>
									</tr>
									<tr>
										<th class="text-nowrap">列排序</th>
										<td colspan="4">是</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>演示说明</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<div class="center">
							<div class="row">
								<div class="col-xs-12">
									<span>.col-xs-12</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-1">
									<span>.col-xs-1</span>
								</div>
								<div class="col-xs-11">
									<span>.col-xs-11</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6 col-sm-2">
									<span>.col-xs-6.col-sm-2</span>
								</div>
								<div class="col-xs-6 col-sm-10">
									<span>.col-xs-6.col-sm-10</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12 col-lg-6">
									<span>.col-xs-12.col-lg-6</span>
								</div>

								<div class="col-xs-12 col-lg-6">
									<span>.col-xs-12.col-lg-6</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-4">
									<span>.col-xs-4</span>
								</div>
								<div class="col-xs-8">
									<span>.col-xs-8</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-5">
									<span>.col-xs-5</span>
								</div>
								<div class="col-xs-7">
									<span>.col-xs-7</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-6">
									<span>.col-xs-6</span>
								</div>
								<div class="col-xs-6">
									<span>.col-xs-6</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-7">
									<span>.col-xs-7</span>
								</div>
								<div class="col-xs-5">
									<span>.col-xs-5</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-8">
									<span>.col-xs-8</span>
								</div>
								<div class="col-xs-4">
									<span>.col-xs-4</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-9">
									<span>.col-xs-9</span>
								</div>
								<div class="col-xs-3">
									<span>.col-xs-3</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-10">
									<span>.col-xs-10</span>
								</div>
								<div class="col-xs-2">
									<span>.col-xs-2</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-11">
									<span>.col-xs-11</span>
								</div>
								<div class="col-xs-1">
									<span>.col-xs-1</span>
								</div>
							</div>
							<div class="row">
								<div class="col-xs-12">
									<span>.col-xs-12</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>从堆叠到水平排列</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>使用单一的一组 <code>.col-md-*</code> 栅格类，就可以创建一个基本的栅格系统，在手机和平板设备上一开始是堆叠在一起的（超小屏幕到小屏幕这一范围），在桌面（中等）屏幕设备上变为水平排列。所有“列（column）必须放在 ” <code>.row</code> 内。</p>
						<div class="row show-grid">
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
							<div class="col-md-1">.col-md-1</div>
						</div>
						<div class="row show-grid">
							<div class="col-md-8">.col-md-8</div>
							<div class="col-md-4">.col-md-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-md-4">.col-md-4</div>
							<div class="col-md-4">.col-md-4</div>
							<div class="col-md-4">.col-md-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-md-6">.col-md-6</div>
							<div class="col-md-6">.col-md-6</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>移动设备和桌面屏幕</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>是否不希望在小屏幕设备上所有列都堆叠在一起？那就使用针对超小屏幕和中等屏幕设备所定义的类吧，即 <code>.col-xs-*</code> 和 <code>.col-md-*</code>。请看下面的实例，研究一下这些是如何工作的。</p>
						<div class="row show-grid">
							<div class="col-xs-12 col-md-8">.col-xs-12 .col-md-8</div>
							<div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
							<div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
							<div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-xs-6">.col-xs-6</div>
							<div class="col-xs-6">.col-xs-6</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>手机、平板、桌面</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>在上面案例的基础上，通过使用针对平板设备的 <code>.col-sm-*</code> 类，我们来创建更加动态和强大的布局吧。</p>
						<div class="row show-grid">
							<div class="col-xs-12 col-sm-6 col-md-8">.col-xs-12
								.col-sm-6 .col-md-8</div>
							<div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
							<div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
							<!-- Optional: clear the XS cols if their content doesn't match in height -->
							<div class="clearfix visible-xs"></div>
							<div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>多余的列（column）将另起一行排列</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>在等宽的4网格中，网格不等高会碰到问题，为了解决这个问题，可使用<code>.clearfix</code></p>
						<div class="row show-grid">
							<div class="col-xs-6 col-sm-3">
								.col-xs-6 .col-sm-3 <br>调整窗口大小或者在手机上查看本示例
							</div>
							<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
							<!-- Add the extra clearfix for only the required viewport -->
							<div class="clearfix visible-xs"></div>
							<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
							<div class="col-xs-6 col-sm-3">.col-xs-6 .col-sm-3</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>列偏移</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>使用 <code>.col-md-offset-*</code> 类可以将列向右侧偏移。这些类实际是通过使用 <code>*</code> 选择器为当前元素增加了左侧的边距（margin）。例如，<code>.col-md-offset-4</code> 类将 <code>.col-md-4</code> 元素向右侧偏移了4个列（column）的宽度。</p>
						<div class="row show-grid">
							<div class="col-md-4">.col-md-4</div>
							<div class="col-md-4 col-md-offset-4">.col-md-4
								.col-md-offset-4</div>
						</div>
						<div class="row show-grid">
							<div class="col-md-3 col-md-offset-3">.col-md-3
								.col-md-offset-3</div>
							<div class="col-md-3 col-md-offset-3">.col-md-3
								.col-md-offset-3</div>
						</div>
						<div class="row show-grid">
							<div class="col-md-6 col-md-offset-3">.col-md-6
								.col-md-offset-3</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>嵌套列</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>为了使用内置的栅格系统将内容再次嵌套，可以通过添加一个新的 <code>.row</code> 元素和一系列 <code>.col-sm-*</code> 元素到已经存在的 <code>.col-sm-*</code> 元素内。被嵌套的行（row）所包含的列（column）的个数不能超过12（其实，没有要求你必须占满12列）。</p>
						<div class="row show-grid">
							<div class="col-md-9">
								第一级： .col-md-9
								<div class="row show-grid">
									<div class="col-md-6">第二级： .col-md-6</div>
									<div class="col-md-6">第二级： .col-md-6</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						 <h5>列排序</h5>
						 <div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a> 
							<a class="close-link">
								<i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<p>通过使用 <code>.col-md-push-*</code> 和 <code>.col-md-pull-*</code> 类就可以很容易的改变列（column）的顺序。</p>
						<div class="row show-grid">
							<div class="col-md-9 col-md-push-3">.col-md-9
								.col-md-push-3</div>
							<div class="col-md-3 col-md-pull-9">.col-md-3
								.col-md-pull-9</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript" src="${_currConText }/plug-in/jquery/jquery-1.10.2.min.js"></script>
<!-- 自定义js -->
<script src="${_currConText }/plug-in/tools/ExtJavascript.js?v=1.0.0"></script>
<script src="${_currConText }/plug-in/tools/content.js?v=1.0.0"></script>
</html>
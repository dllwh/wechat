<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<title>HTML5 多媒体播放器</title>
<%@ include file="/WEB-INF/webviews/common/context/bootstrap.jsp"%>
<link rel="stylesheet" href="${_currConText }/static/css/sample/style.css" />
</head>
<body>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox">
					<div class="ibox-title">
						<h5>
							HTML5 多媒体播放器 <a href="https://plyr.io/" target="_blank">https://plyr.io/</a>
						</h5>
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
						<p>一个使用 HTML5 开发的基于浏览器上的多媒体播放器。</p>
						<p>特性：</p>
						<ul>
							<li>
								<p>
									<strong>可访问性</strong> - 完全支持字幕
								</p>
							</li>
							<li>
								<p>
									<strong>语义化</strong> - 使用 HTML5 的输入框进行音量和进度的调整
								</p>
							</li>
							<li>
								<p>
									<strong>向下兼容</strong> - 如果浏览器不支持，则自动使用内建播放器
								</p>
							</li>
							<li>
								<p>
									<strong>全屏支持</strong> -支持原生全屏和退出全屏
								</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
				<div class="col-sm-4">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>直播房间列表</h5>
						<div class="ibox-tools">
							<a class="collapse-link">
								<i class="fa fa-chevron-up"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<ul class="list-group">
							<li class="list-group-item">
								免费域名注册<span class="pull-right label label-default">1</span>
							</li>
							<li class="list-group-item">
								免费 Window空间托管<span class="pull-right label label-primary">2</span>
							</li>
							<li class="list-group-item">
								图像的数量<span class="pull-right label label-success">3</span>
							</li>
							<li class="list-group-item">
								24*7 支持<span class="pull-right label label-info">4</span>
							</li>
							<li class="list-group-item">
								每年更新成本<span class="pull-right label label-warning">5</span>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>音频播放</h5>
					</div>
					<div class="ibox-content">
						<div class="player">
							<audio controls src="http://music.baidu.com/cms/app/muplayer/test_mp3/1.mp3">
							</audio>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>视频播放</h5>
					</div>
					<div class="ibox-content">
						<div class="player">
							<video controls autoplay src="https://vplscdn.videojj.com/video/zongyi.mp4" loop>
							</video>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<%@ include file="/WEB-INF/webviews/common/footer.jsp"%>
</html>
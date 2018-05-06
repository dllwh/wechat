<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/webviews/common/common.jsp"%>
<%@ include file="/WEB-INF/webviews/common/header.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/ace.jsp"%>
<%@ include file="/WEB-INF/webviews/common/context/assets.jsp"%>
<link rel="stylesheet" href="${_currConText }/plug-in/font/css/font-awesome.min.css" />
 <link rel="stylesheet" href="${_currConText }/static/css/payment.css"/>  
<title>支付详细信息</title>
</head>
<body>
	<div class="margin clearfix">
		<div class="Payment_details">
			<div class="brand_detailed">
				<div class="brand_info clearfix">
					<div class="title_brand">支付信息</div>
					<form>
						<ul class="Info_style clearfix">
							<li>
								<label class="label_name">支付名称：</label>
								<span class="name">${paymentConfig.name}</span>
							</li>
							<li>
								<label class="label_name">开通时间：</label>
								<span class="name">
									<fmt:formatDate value="${paymentConfig.openTime}" />
								</span>
							</li>
							<li>
								<label class="label_name">结束时间：</label>
								<span class="name">
									<fmt:formatDate value="${paymentConfig.endTime}" />
								</span>
							</li>
							<li>
								<label class="label_name">所属国家：</label>
								<span class="name">${paymentConfig.source }</span>
							</li>
							<li>
								<label class="label_name">交易类型：</label>
								<span class="name">${paymentConfig.itemName }</span>
							</li>
							<li>
								<label class="label_name">状&nbsp;&nbsp;&nbsp;&nbsp;态：</label>
								<c:if test="${paymentConfig.ifVisible eq 1 }">
									<span class="name">启用</span>
								</c:if>
								<c:if test="${paymentConfig.ifVisible eq 0 }">
									<span class="name">禁用</span>
								</c:if>
							</li>
							<li class="b_Introduce">
								<label class="label_name">支付介绍：</label>
								<span class="name">${paymentConfig.remark}。</span>
							</li>
						</ul>
						<div class="brand_logo">
							<img src="${_currConText }/static/image/bank/yinglian.jpg" width="120px" height="60px">
							<p class="name">${paymentConfig.name}</p>
						</div>
					</form>
				</div>
			</div>
			<div class="bank_style clearfix" id="Bank_operations">
				<div class="title_name">支持银行</div>
				<div class="operating">
					<c:if test="${not empty paymentBank }">					
						<a onclick="modify()" class="btn btn-danger btn_delete">
							<i class="fa fa-trash"></i>&nbsp;删除操作
						</a>
					</c:if>
					<a onclick="Add_Bank()" class="btn btn-primary">
						<i class="fa fa-credit-card"></i>&nbsp;添加银行
					</a>
				</div>
				<div class="bank_list clearfix">
					<ul class="bank_logo clearfix">
						<c:forEach items="${paymentBank }" var="item">
							<li>
								<div class="delete_bank">
									<a onclick="bank_del(this,'${item.id}')" class="fa fa-remove"></a>
								</div>
								<label>
									<input name="form-field-checkbox" type="checkbox" class="ace">
									<span class="lbl">
										<img src="${_currConText }${item.iconPath}" width="160px;" />
									</span>
								</label>
							</li>
						</c:forEach>
					</ul>
					<div class="Push_button"></div>
				</div>
			</div>
		</div>
	</div>
	<!--添加银行-->
	<div id="add_bankstyle" style="display: none">
		<div class="prompt">请确保你开通了该银行的付款业务，如果未开通，可能造成用户付款失败。</div>
		<div class="add_bank_list margin">
			<ul>
				<c:forEach items="${notExistPayBank }" var="item">
					<li>
						<label>
							<input name="form-field-checkbox" type="checkbox" class="ace">
							<span class="lbl">
								<img src="${_currConText }${item.iconPath}" width="160px;" />
							</span>
						</label>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>

<script type="text/javascript">
	
	$('#Bank_operations').find('.Push_button').html('<label><input name="checkbox" type="checkbox" class="ace" id="select_all"><span class="lbl">全选</span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;');
	/**全选**/
	$('#select_all').on('click' , function(){
		var that = this;
		$(this).closest('.bank_list').find('li input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('li').toggleClass('selected');
		});
			
	});
	
	//按钮点击事件
	function modify() {
		$('.ace').attr("disabled", false);
		$('.ace').addClass("add");
		$('#Bank_operations').find('.bank_logo').addClass("delete");
		$('#Bank_operations').find('.Push_button').addClass("btn_delete");
		$('#Bank_operations').find('.operating .btn_delete').hide();
		$('#Bank_operations').find('.Push_button').prepend('<span class="Button"><a style="text-decoration:none" class="btn btn-success" onClick="bank_complete(this,id)" href="javascript:;" title="启用"><i class=""></i>确认操作</a></span>');
	}
	
	/*确认操作*/
	function bank_complete(obj, id) {
		var checkbox=$('input[name="form-field-checkbox"]');
		if(checkbox.length){
			for(var i=0; i<checkbox.length; i++){ 
				if(checkbox[i].checked){
					layer.alert('删除成功！',{
						title: '提示框',				
						icon:0,		
					})
					
					break;					
				} else {
					$('.ace').removeClass("add");
					$('#Bank_operations').find('.Push_button').removeClass("btn_delete");
					$('#Bank_operations').find('.bank_logo').removeClass("delete");
					$('#Bank_operations').find('.Push_button .Button').empty();
					$('#Bank_operations').find('.operating .btn_delete').show();
				}
			}	 		
		}
		
		if($('input[name="checkbox"]').prop("checked")){ 
			layer.alert('是否删除全部银行！',{
				title: '提示框',				
				icon:0,
				btn:['确定','取消'],	
				yes: function(index){
					$('#Bank_operations').find('.bank_logo').empty();
					$('#Bank_operations').find('.Push_button').empty();					   
					layer.close(index);  
				}		
			}); 			  
		}	
	}
	
	/**添加银行操作**/
	function Add_Bank(index) {
		$('input[name="form-field-checkbox"]').removeAttr('checked');
		var index = layer.open({
			type: 1,
			title: '添加银行',
			maxmin: true, 
			shadeClose:false,
			area : ['830px' , ''],
			content:$('#add_bankstyle'),
			btn:['确定','取消'],
			yes:function(index, layero){
				var checkbox=$('input[name="form-field-checkbox"]');
				if(checkbox.length){
					for(var i=0; i<checkbox.length; i++){ 
						if(checkbox[i].checked){
							layer.alert('添加成功！',{
								title: '提示框',				
								icon:0,		
							}); 
							layer.close(index); 
							break;					
						} else {
							layer.alert('请选择银行！',{
								title: '提示框',				
								icon:0,		
							});
						}
					}
				} 
			}		  
		});
	}
	
	/*银行-删除*/
	function bank_del(obj, id) {
		layer.confirm('确认要删除该银行吗？',function(index){
			$(obj).parents("li").remove();
			layer.msg('已删除!',{icon:1,time:1000});
		});
	}
</script>
</html>
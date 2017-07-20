<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- <script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	$(function(){
		$("#find").submit(function(){
			var lp = $("#lprice").val;
			var hp = $("#hprice").val;
			if(isNaN(lp) || isNaN(hp)){
				return false;
			}
		});
	});
</script> --%>

<script type="text/javascript">
	function checkPrice(){
		alert("asd");
		var lp = document.getElementById("lprice").value;
		var hp = document.getElementById("hprice").value;
		if(isNaN(lp) || isNaN(hp)){
			return false;
		}
	}
</script>


<div class="span10 last">
	<div class="topNav clearfix">
		<ul>
			<c:if test="${sessionScope.existUser == null}">
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<a href="${ pageContext.request.contextPath }/user_loginPage">登录</a>|</li>
			<li id="headerRegister" class="headerRegister"
				style="display: list-item;"><a href="${ pageContext.request.contextPath }/user_registPage">注册</a>|
			</li>
			</c:if>
			<c:if test="${sessionScope.existUser != null}">
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				${sessionScope.existUser.name }
				|</li>
			<li id="headerLogin" class="headerLogin" style="display: list-item;">
				<a href="${ pageContext.request.contextPath }/order_findByUid?page=1">我的订单</a>
			|</li>
			<li id="headerRegister" class="headerRegister"
				style="display: list-item;"><a href="${ pageContext.request.contextPath }/user_exit">退出</a>|
			</li>
			</c:if>
		
			<li><a>会员中心</a> |</li>
			<li><a>购物指南</a> |</li>
			<li><a>关于我们</a></li>
		</ul>
	</div>
	<div class="cart">
		<a href="${ pageContext.request.contextPath }/cart_myCart">购物车</a>
	</div>
	<div class="phone">
		客服热线: <strong>96008/53277764</strong>
	</div>
</div>
<div class="span24">
	<ul class="mainNav">
		<li><a href="${ pageContext.request.contextPath }/index">首页</a> |</li>
		<c:forEach var="cl" items="${sessionScope.cList}">
			<li><a href="${ pageContext.request.contextPath }/product_findByCid?cid=${cl.cid }&page=1">${cl.cname }</a> |</li>
		</c:forEach>
	</ul>
	<form action="${pageContext.request.contextPath }/search" method="post" onsubmit="return checkPrice();" id="find">
		<input type="hidden" name="page" value="1"/>
		商品价格:<input id="lprice" name = "lprice" <c:if test="${lprice!=null}">value ="${lprice }"</c:if> />-<input id="hprice" name = "hprice" <c:if test="${hprice!=null}">value ="${hprice }"</c:if>/>
		商品名称:<input name = "prname" <c:if test="${prname!=null}">value ="${prname }"</c:if> />
		<input type="submit" value="搜索"/>
	</form>
</div>
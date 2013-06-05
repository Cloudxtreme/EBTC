<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="cos" uri="/constantsTag" %>  

<%@include file="/WEB-INF/pages/common/headder.jsp" %>

<script type="text/javascript">
</script>

	<!-- Page content -->
	<div id="page">
		<!-- Wrapper -->
		<div class="wrapper">
				<!-- Left column/section -->
				<section class="column width6 first">
<!-- 					欢迎回来，账户信息，余额 -->
					<div class="colgroup leading">
						<div class="column width3 first">
							<h3><fmt:message key="welcomeBack"/>, <a href="#">${sessionScope.login_user.nickname }</a>!</h3>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="totalBtcBalance"/>: <b><fmt:formatNumber type="currency" pattern="฿#,###.#########" value="${requestScope.btcAccount.balance }"/></b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="usableBtcBalance"/>: <b><fmt:formatNumber type="currency" pattern="฿#,###.#########" value="${requestScope.btcAccount.balance - requestScope.btcAccount.freeze}"/></b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="freezeBtcBalance"/>: <b><fmt:formatNumber type="currency" pattern="฿#,###.#########" value="${requestScope.btcAccount.freeze }"/></b><br />
							</p>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="totalCnyBalance"/>: <b><fmt:formatNumber type="currency" pattern="￥#,###.##" value="${requestScope.cnyAccount.balance }"/></b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="usableCnyBalance"/>: <b><fmt:formatNumber type="currency" pattern="￥#,###.##" value="${requestScope.cnyAccount.balance - requestScope.cnyAccount.freeze}"/></b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;<fmt:message key="freezeCnyBalance"/>: <b><fmt:formatNumber type="currency" pattern="￥#,###.##" value="${requestScope.cnyAccount.freeze }"/></b><br />
							</p>
						</div>
						<div class="column width3">
							<h4><fmt:message key="lastLoginTime"/></h4>
							<p>
								<fmt:formatDate  value="${sessionScope.login_user.lastLoginTime }" type="both" timeStyle="default" pattern="EEEE, MMMM d, yyyy HH:mm:ss"/>
								<br />
							</p>
						</div>
					</div>
<!-- 					充值 提现-->
					<div class="colgroup leading">
						<div class="column width3 first">
							<h3><fmt:message key="pay"/></h3>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;您的比特币余额: <b>฿20 </b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;冻结比特币余额: <b>฿20 </b>
							</p>
						</div>
						<div class="column width3">
							<h3><fmt:message key="withdraw" /></h3>
							<p>
								Monday July 12th, 2010 at 11:32am from 127.0.0.1<br />
								No error login attempts.
							</p>
						</div>
					</div>
<!-- 					我的委托单 -->
					<div class="leading clearfix">
						<div class="column width6 first">
							<div class="content-box corners content-box">
								<header>
									<h3><fmt:message key="myOrders"/></h3>
								</header>
								<section class="W_98">
									<table class="no-style full center">
										<thead>
											<tr>
												<th><fmt:message key="orderTime"/></th>
												<th><fmt:message key="tradeType"/></th>
												<th><fmt:message key="price"/></th>
												<th><fmt:message key="quantity"/></th>
												<th><fmt:message key="totalAmount"/></th>
												<th><fmt:message key="state"/></th>
												<th><fmt:message key="operations"/></th>
											</tr>
										</thead>
										<tbody>
											<c:choose>
												<c:when test="${not empty requestScope.myOrderList }">
													<c:forEach items="${requestScope.myOrderList }" var="order" varStatus="status">
														<tr>
															<td><fmt:formatDate value="${order.createTime }" type="both" timeStyle="default" /></td>
															<td>
																<c:choose>
																	<c:when test="${order.buyerId eq sessionScope.login_user.id }">
																		buy
																	</c:when>
																	<c:otherwise>
																		sell
																	</c:otherwise>
																</c:choose>
															</td>
															<td><fmt:formatNumber type="currency" pattern="￥#,###.##" value="${order.price }"/></td>
															<td><fmt:formatNumber type="currency" pattern="฿#,###.#########" value="${order.quantity }"/></td>
															<td><fmt:formatNumber type="currency" pattern="￥#,###.##" value="${order.price * order.quantity }"/></td>
															<td>${cos:display('STATE',order.state) }</td>
															<td></td>
														</tr>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td colspan="7">
															<fmt:message key="noRecords"/>
														</td>
													</tr>
												</c:otherwise>
											</c:choose>
											
										</tbody>
										<tfoot>
											<tr>
												<td>footer 1</td>
												<td>footer 2</td>
												<td>footer 3</td>
												<td>footer 5</td>
											</tr>
										</tfoot>
									</table>
								</section>
							</div>
						
						</div>
						
					</div>
					
					<div class="clear">&nbsp;</div>
					
				</section>
				<!-- End of Left column/section -->
				
				<!-- Right column/section -->
				<aside class="column width2">
<!-- 					<div class="content-box corners"> -->
<!-- 						<header> -->
<!-- 							<h3>聊天窗口</h3> -->
<!-- 						</header> -->
<!-- 						<section class="full"> -->
<!-- 							<table class="no-style full"> -->
<!-- 								<tbody> -->
<!-- 									<tr> -->
<!-- 										<td class="ta-left">Data 2</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>Das:asdfasdflkja sdfkj asldfj lkasjdflk alsjf lasjd</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>Data 1</td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>Data 1</td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!-- 								<tfoot> -->
<!-- 									<tr> -->
<!-- 										<td><input/>send</td> -->
<!-- 									</tr> -->
<!-- 								</tfoot> -->
<!-- 							</table> -->
<!-- 						</section> -->
<!-- 					</div> -->
<!-- 					实时委单 -->
					<div class="content-box corners">
						<header>
							<h3>实时价格</h3>
						</header>
						<section class="full">
							<table class="no-style full">
								<tbody class="red_td">
									<tr>
										<td>买(5)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>买(4)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>买(3)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>买(2)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>买(1)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
								</tbody>
								<tfoot class="green_td">
									<tr>
										<td >卖(1)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>卖(2)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>卖(3)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>卖(4)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
									<tr>
										<td>卖(5)</td>
										<td>￥700</td>
										<td>฿1.04</td>
									</tr>
								</tfoot>
							</table>
						</section>
					</div>
<!-- 					联系客服 -->
					<div class="content-box corners">
						<header>
							<h3>联系客服</h3>
						</header>
						<section class="full">
							<table class="no-style full">
								<tbody>
									<tr>
										<td>XXXX</td>
										<td>在线</td>
										<td></td>
									</tr>
									<tr>
										<td>XXXX</td>
										<td>在线</td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</section>
					</div>
					<div class="clear">&nbsp;</div>
				</aside>
				<!-- End of Right column/section -->
				
		</div>
		<!-- End of Wrapper -->
	</div>
	<!-- End of Page content -->
	
<%@include file="/WEB-INF/pages/common/footer.jsp" %>
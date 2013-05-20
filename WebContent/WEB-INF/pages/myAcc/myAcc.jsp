<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/pages/common/headder.jsp" %>

<script type="text/javascript">
	$(document).ready(function(){
		
		/* setup navigation, content boxes, etc... */
		Administry.setup();
		
	});
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
							<h3>欢迎回来, <a href="#">Admin</a>!</h3>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;您的比特币余额: <b>฿20 </b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;冻结比特币余额: <b>฿20 </b>
							</p>
						</div>
						<div class="column width3">
							<h4>上次登陆时间</h4>
							<p>
								Monday July 12th, 2010 at 11:32am from 127.0.0.1<br />
								No error login attempts.
							</p>
						</div>
					</div>
<!-- 					充值 提现-->
					<div class="colgroup leading">
						<div class="column width3 first">
							<h3>充值</h3>
							<p>
								&nbsp;&nbsp;&nbsp;&nbsp;您的比特币余额: <b>฿20 </b><br />
								&nbsp;&nbsp;&nbsp;&nbsp;冻结比特币余额: <b>฿20 </b>
							</p>
						</div>
						<div class="column width3">
							<h3>提现</h3>
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
									<h3>我的委托单</h3>
								</header>
								<section class="W_98">
									<table class="no-style full center">
										<thead>
											<tr>
												<th>下单时间</th>
												<th>交易类型</th>
												<th>交易价格(CNY/BTC)</th>
												<th>数量(BTC)</th>
												<th>总额(CNY)</th>
												<th>状态</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>Data 1</td>
												<td>Data 2</td>
												<td>Data 3</td>
												<td>Data 4</td>
											</tr>
											<tr>
												<td>Data 1</td>
												<td>Data 2</td>
												<td>Data 3</td>
												<td>Data 5</td>
											</tr>
											<tr>
												<td>Data 1</td>
												<td>Data 2</td>
												<td>Data 3</td>
												<td>Data 4</td>
											</tr>
											<tr>
												<td>Data 1</td>
												<td>Data 2</td>
												<td>Data 3</td>
												<td>Data 5</td>
											</tr>
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
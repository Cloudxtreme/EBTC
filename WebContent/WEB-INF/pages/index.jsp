<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="/WEB-INF/pages/common/headder.jsp" %>
<!-- jQuery graph plugins -->
<script type="text/javascript" src="${ctx}/js/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="${ctx}/js/flot/jquery.flot.min.js"></script>
<script type="text/javascript" src="${ctx}/js/flot/jquery.flot.selection.min.js"></script>
<!-- JSON -->
<script type="text/javascript" src="${ctx}/js/json/json2.min.js"></script>
<!-- WS_OPERATIONS_COMMUNICATIONS -->
<script type="text/javascript" src="${ctx}/js/ebtc/operations_communications.js"></script>
<script type="text/javascript">
	
	
	$(document).ready(function(){
		
		serviceRequest(["currentRate","transactionRecord"]);
		
		$("#buyPrice").keypress(function(event) {  
            var keyCode = event.keyCode || event.which;
            if (keyCode == 8 || keyCode == 46 || (keyCode >= 48 && keyCode <=57)){
                return true;  
            }else{
                return false;  
            }  
        }).focus(function() {  
            this.style.imeMode='disabled';  
        }).bind("keyup",function(){
        	computePrice('buy');
        });
		
		$("#buyQuantity").keypress(function(event) {  
            var keyCode = event.keyCode || event.which;
            if (keyCode == 8 || keyCode == 46 || (keyCode >= 48 && keyCode <=57)){
                return true;  
            }else{
                return false;  
            }  
        }).focus(function() {  
            this.style.imeMode='disabled';  
        }).bind("keyup",function(){
        	computePrice('buy');
        });;
        
        
        $("#sellPrice").keypress(function(event) {  
            var keyCode = event.keyCode || event.which;
            if (keyCode == 8 || keyCode == 46 || (keyCode >= 48 && keyCode <=57)){
                return true;  
            }else{
                return false;  
            }  
        }).focus(function() {  
            this.style.imeMode='disabled';  
        }).bind("keyup",function(){
        	computePrice('sell');
        });
		
		$("#sellQuantity").keypress(function(event) {  
            var keyCode = event.keyCode || event.which;
            if (keyCode == 8 || keyCode == 46 || (keyCode >= 48 && keyCode <=57)){
                return true;  
            }else{
                return false;  
            }  
        }).focus(function() {  
            this.style.imeMode='disabled';  
        }).bind("keyup",function(){
        	computePrice('sell');
        });;
        
        
        
		
		var fh_data = [
		{
			label: 'max',
			data: [[1267873200 * 1000, 2],[1267959600 * 1000, 2],[1268046000 * 1000, 2],[1268132400 * 1000, 3],[1268218800 * 1000, 3],[1268305200 * 1000, 3],[1268391600 * 1000, 3],[1268478000 * 1000, 5],[1268564400 * 1000, 7],[1268650800 * 1000, 4],[1268737200 * 1000, 7],[1268823600 * 1000, 7],[1268910000 * 1000, 12],[1268996400 * 1000, 14],[1269082800 * 1000, 16],[1269169200 * 1000, 18],[1269255600 * 1000, 14],[1269342000 * 1000, 15],[1269428400 * 1000, 17],[1269514800 * 1000, 18],[1269601200 * 1000, 20],[1269687600 * 1000, 16],[1269774000 * 1000, 14],[1269856800 * 1000, 17],[1269943200 * 1000, 19],[1270029600 * 1000, 15],[1270116000 * 1000, 16],[1270202400 * 1000, 13],[1270288800 * 1000, 16],[1270375200 * 1000, 19],[1270461600 * 1000, 13],[1270548000 * 1000, 14],[1270634400 * 1000, 15]]
		},
		{
			label: 'min',
			data: [[1267873200 * 1000, -3],[1267959600 * 1000, -3],[1268046000 * 1000, -2],[1268132400 * 1000, -2],[1268218800 * 1000, 0],[1268305200 * 1000, 0],[1268391600 * 1000, -2],[1268478000 * 1000, 0],[1268564400 * 1000, 2],[1268650800 * 1000, 0],[1268737200 * 1000, -1],[1268823600 * 1000, 2],[1268910000 * 1000, 4],[1268996400 * 1000, 6],[1269082800 * 1000, 9],[1269169200 * 1000, 11],[1269255600 * 1000, 9],[1269342000 * 1000, 9],[1269428400 * 1000, 8],[1269514800 * 1000, 10],[1269601200 * 1000, 11],[1269687600 * 1000, 7],[1269774000 * 1000, 6],[1269856800 * 1000, 9],[1269943200 * 1000, 11],[1270029600 * 1000, 7],[1270116000 * 1000, 7],[1270202400 * 1000, 5],[1270288800 * 1000, 6],[1270375200 * 1000, 9],[1270461600 * 1000, 8],[1270548000 * 1000, 5],[1270634400 * 1000, 7]]
		}
		];
		function weekendAreas(plotarea) {
			var areas = [];
			var d = new Date(plotarea.xmin);
			// go to the first Saturday
			d.setDate(d.getDate() - ((d.getDay() + 1) % 7))
			d.setSeconds(0);
			d.setMinutes(0);
			d.setHours(0);
			var i = d.getTime();
			do {
				areas.push({ x1: i, x2: i + 2 * 24 * 60 * 60 * 1000 });
				i += 7 * 24 * 60 * 60 * 1000;
			} while (i < plotarea.xmax);

			return areas;
		}
		function showTooltip(x, y, contents) {
			$('<div id="hovertip">' + contents + '</div>').css( {
				position: 'absolute',
				display: 'none',
				top: y + 5,
				left: x + 15,
				border: '2px solid #666',
				padding: '4px',
				'background-color': '#fff',
				opacity: 0.9,
				color: '#666',
				fontSize: '13px'
			}).appendTo("body").fadeIn('fast');
		}
		
		var options = {
			lines: { show: true, lineWidth: 3 },
			points: { show: true },
			legend: { noColumns: 2, position: "se"/*, container: '#flot-legend'*/ },
			yaxis: { min: -25, max: 25 },
			xaxis: { mode: "time", timeformat: "%d %b", monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"] },
			selection: { mode: "x" },
			grid: { color: "#666", coloredAreas: weekendAreas, hoverable: true },
			colors: ["#E92424", "#75C5F0"]			
		};
		
		var plot = $.plot($("#flotPlaceholder"), fh_data, options);
		
		$("#flotPlaceholder").bind("selected", function (event, area) {
			plot = $.plot($("#flotPlaceholder"), fh_data,
				  $.extend(true, {}, options, {
					  xaxis: { min: area.x1, max: area.x2 }
				  }));
			$('#clearSelection').show();
		});
		var previousPoint = null;
		$("#flotPlaceholder").bind("plothover", function (event, pos, item) {
			if (item) {
				if (previousPoint != item.datapoint) {
					previousPoint = item.datapoint;
					
					$("#hovertip").remove();
					var y = item.datapoint[1];
					
					showTooltip(item.pageX, item.pageY, y + '°C');
				}
			}
			else {
				$("#hovertip").remove();
				previousPoint = null;            
			}
		});
		$("#clearSelection").click(function () {
			$.plot($("#flotPlaceholder"), fh_data, options);
			$('#clearSelection').hide();
		});

		$('#news').click(function(event){
			event.stopPropagation();
			return false;
			
		})
	});
</script>
	<!-- Page content -->
	<div id="page">
		<!-- Wrapper -->
		<div class="wrapper">
				<!-- Left column/section -->
				<section class="column width6 first">
<!-- 					NEWS -->
					<div class="leading clearfix">
						<div class="column width6 first">
							<div class="content-box corners">
								<header id="news">
									<h3><fmt:message key="News"/></h3>
								</header>
								<section class="W_98">
									<table class="no-style full">
										<tbody>
											<tr>
												<td><b>易比特支持人民币充值提现</b></td>
												<td class="ta-right">2013-05-37</td>
											</tr>
											<tr>
												<td><b>Posts</b></td>
												<td class="ta-right">22</td>
											</tr>
											<tr>
												<td><b>Comments</b></td>
												<td class="ta-right">332</td>
											</tr>
										</tbody>
									</table>
								</section>
							</div>
						</div>
					</div>
<!-- 					走势图 -->
					<h4>行情走势图</h4>
					<div id="flotPlaceholder" style="height:300px;"></div>
					<input id="clearSelection" type="button" class="btn" value="Zoom out" style="display:none"/>
<!-- 					控制按钮 -->
					<p>&nbsp;</p>
					
<!-- 					交易记录窗口 -->
					<div class="leading clearfix">
						<div class="column width6 first">
							<div class="content-box corners content-box">
								<header>
									<h3><fmt:message key="TransactionRecords"/></h3>
								</header>
								<section class="W_98">
									<table class="no-style full center">
										<thead>
											<tr>
												<th>交易时间</th>
												<th>交易类型</th>
												<th>成交价格</th>
												<th>数量</th>
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
							<h3><fmt:message key="CurrentRate"/></h3>
						</header>
						<section class="full">
							<table class="no-style full">
								<tbody id="highPriceBuy" class="red_td">
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
								<tfoot id="lowPriceSell" class="green_td">
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
<!-- 					买入BTC -->
					<div class="content-box corners">
						<header>
							<h3><fmt:message key="BuyBTC"/></h3>
						</header>
						<section class="full">
							<input type="hidden" id="buyHandlingFeeRate" value="${applicationScope.buyHandlingFeeRate }"/>
							<input type="hidden" id="buyBestPrice" value="0")/>
							<table class="no-style full">
								<tbody>
									<tr>
										<td>当前余额</td>
										<td id="buyUsableBalance">${sessionScope.cny_account.balance - sessionScope.cny_account.freeze + 0}</td>
										<td>CNY</td>
									</tr>
									<tr>
										<td>可买入数量</td>
										<td id="buyableQuantity">0</td>
										<td>BTC</td>
									</tr>
									<tr>
										<td>买入价格</td>
										<td><input id="buyPrice" style="width:50px" value="" type="number" /></td>
										<td>CNY/BTC</td>
									</tr>
									<tr>
										<td>买入数量</td>
										<td><input id="buyQuantity" style="width:50px" value="" type="number"/></td>
										<td>BTC</td>
									</tr>
									<tr>
										<td>总额</td>
										<td id="buyTotalSpend">0</td>
										<td>CNY</td>
									</tr>
									<tr>
										<td>手续费</td>
										<td id="buyHandlingFee">0</td>
										<td>CNY(<fmt:formatNumber type="currency" pattern="#,###.##%" value="${applicationScope.buyHandlingFeeRate }"/>)</td>
									</tr>
									<tr class="center">
										<td colspan="3">
											<a class="btn btn-red" onclick="newOrder('buy');">确认购买(CNY-BTC)</a>
										</td>
									</tr>
								</tbody>
							</table>
						</section>
					</div>
					
<!-- 					卖出BTC -->
					<div class="content-box corners">
						<header>
							<h3><fmt:message key="SellBTC"/></h3>
						</header>
						<section class="full">
							<input type="hidden" id="sellHandlingFeeRate" value="${applicationScope.sellHandlingFeeRate }"/>
							<input type="hidden" id="sellBestPrice" value="0")/>
							<table class="no-style full">
								<tbody>
									<tr>
										<td>当前余额</td>
										<td id="sellUsableBalance">${sessionScope.btc_account.balance - sessionScope.btc_account.freeze + 0}</td>
										<td>BTC</td>
									</tr>
									<tr>
										<td>卖出价格</td>
										<td><input id="sellPrice" style="width:50px" /></td>
										<td>CNY/BTC</td>
									</tr>
									<tr>
										<td>卖出数量</td>
										<td><input id="sellQuantity" style="width:50px" /></td>
										<td>BTC</td>
									</tr>
									<tr>
										<td>总额</td>
										<td id="totalSellAmount">0</td>
										<td>CNY</td>
									</tr>
									<tr>
										<td>手续费</td>
										<td id="sellHandlingFee">0</td>
										<td>CNY(0.2%)</td>
									</tr>
									<tr class="center">
										<td colspan="3">
											<a class="btn btn-green" onclick="newOrder('sell');">确认卖出(BTC-CNY)</a>
										</td>
									</tr>
								</tbody>
							</table>
						</section>
					</div>
					
<!-- 					联系客服 -->
					<div class="content-box corners">
						<header>
							<h3><fmt:message key="CustomerService"/></h3>
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
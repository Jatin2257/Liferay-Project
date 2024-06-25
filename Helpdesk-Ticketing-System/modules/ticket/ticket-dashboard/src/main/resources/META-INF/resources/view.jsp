<%@ include file="/init.jsp"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>

<style>
.ticket-boxes {
	display: flex;
	justify-content: space-around;
	margin-bottom: 20px;
}

.ticket-box {
	flex: 1;
	padding: 20px;
	margin: 10px;
	text-align: center;
	border-radius: 8px;
	color: white;
}

.chart-container {
	display: flex;
	justify-content: space-between;
}

.chart-box {
	background-color: white;
	border: 1px solid #ddd;
	border-radius: 8px;
	padding: 20px;
	margin: 10px;
	width: 55%;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.total-tickets {
	background-color: #4CAF50;
}

.open-tickets {
	background-color: blue;
}

.closed-tickets {
	background-color: #F44336;
}

.inprogress-tickets {
	background-color: #FFC107;
}

.ticket-boxs {
	display: inline-block;
	width: 30%;
	vertical-align: top;
	padding: 10px;
	margin: 10px;
	border: 1px solid #ccc;
	border-radius: 8px;
	background-color: #f9f9f9;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	min-height: 392px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: center;
}

th {
	background-color: #f2f2f2;
	font-style: italic;
}

.pagination {
	display: flex;
	justify-content: center;
	margin-top: 20px;
}

.pagination a, .pagination button {
	margin: 0 5px;
	padding: 8px 16px;
	text-decoration: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	background-color: #f1f1f1;
	cursor: pointer;
}

.pagination button:disabled {
	background-color: #ccc;
	cursor: not-allowed;
}

.empty-message {
	text-align: center;
	font-weight: bold;
	font-style: italic;
}
</style>


<div class="ticket-boxes">
	<div class="ticket-box total-tickets">
		<h2>Total Tickets</h2>
		<p>${openTickets+closedTickets+inProgressTickets+openTicketDepartment+inProgressTicketDepartment+closedTicketDepartment}</p>
	</div>
	<div class="ticket-box open-tickets">
		<h2>Open Tickets</h2>
		<p>${openTickets+openTicketDepartment}</p>
	</div>
	<div class="ticket-box inprogress-tickets">
		<h2>In-Progress Tickets</h2>
		<p>${inProgressTickets+inProgressTicketDepartment}</p>
	</div>
	<div class="ticket-box closed-tickets">
		<h2>Closed Tickets</h2>
		<p>${closedTickets+closedTicketDepartment}</p>
	</div>
</div>

<div class="chart-container">
	<div class="chart-box">
		<div class="piechart" id="piechart_3d"
			style="width: 100%; height: 300px;"></div>
	</div>
	<div class="chart-box">
		<div id="column_chart" style="width: 100%; height: 300px;"></div>
	</div>
</div>

<div id="ticketContainer">
	<div class="ticket-boxs" id="openTicketsBox">
		<h2 align="center" style="font-style: italic;">Open Tickets</h2>
		<table id="openTicketsTable">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Department</th>
					<th>Category</th>
					<th>Sub Category</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${isDepartment}">
						<c:forEach var="ticket" items="${opendepartmentTicketsList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ticket" items="${openTicketList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="pagination" id="openPagination"></div>
	</div>

	<div class="ticket-boxs" id="inProgressTicketsBox">
		<h2 align="center" style="font-style: italic;">In-Progress
			Tickets</h2>
		<table id="inProgressTicketsTable">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Department</th>
					<th>Category</th>
					<th>Sub Category</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${isDepartment}">
						<c:forEach var="ticket" items="${inProgressdepartmentTicketsList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ticket" items="${inProgressTicketList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="pagination" id="inProgressPagination"></div>
	</div>

	<div class="ticket-boxs" id="closedTicketsBox">
		<h2 align="center" style="font-style: italic;">Closed Tickets</h2>
		<table id="closedTicketsTable">
			<thead>
				<tr>
					<th>Subject</th>
					<th>Department</th>
					<th>Category</th>
					<th>Sub Category</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${isDepartment}">
						<c:forEach var="ticket" items="${closeddepartmentTicketsList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach var="ticket" items="${closedTicketList}">
							<tr>
								<td>${ticket.subject}</td>
								<td>${ticket.department}</td>
								<td>${ticket.category}</td>
								<td>${ticket.subcategory}</td>
								<td>${ticket.status}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<div class="pagination" id="closedPagination"></div>
	</div>
</div>
<script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.load("current", {packages:["bar"]});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawColumnChart);
      
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
        	 ['Status', 'Count'],
             ['Open', ${openTickets+openTicketDepartment}],
             ['Closed', ${closedTickets+closedTicketDepartment}],
             ['In-Progress', ${inProgressTickets+inProgressTicketDepartment}]
        ]);

        var options = {
          title: 'Ticket Data',
          is3D: true,
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
      
      function drawColumnChart() {
          var data, options, chart;

          <c:if test="${isDepartment}">
          	  console.log("is Department");
              data = google.visualization.arrayToDataTable([
                  ['Priority', 'Count'],
                  ['Normal', ${normalPriorityTicketCounts}],
                  ['Medium', ${mediumPriorityTicketCounts}],
                  ['High', ${highPriorityTicketCounts}]
              ]);

              options = {
                  title: 'Ticket Distribution by Priority (Department)',
                  chartArea: { width: '50%' },
                  hAxis: {
                      title: 'Priority'
                  },
                  vAxis: {
                      title: 'Number of Tickets'
                  }
              };
          </c:if>

          <c:if test="${!isDepartment}">
          	  console.log("isnot Department");
              data = new google.visualization.DataTable();
              data.addColumn('string', 'Priority');
              data.addColumn('number', 'Tickets');

              // Add data from the map to the DataTable
              <c:forEach var="entry" items="${ticketCountsByPriority}">
                  var priority = '<c:out value="${entry.key}" />';
                  var ticketCount = ${entry.value};
                  data.addRow([priority, ticketCount]);
              </c:forEach>

              options = {
                  title: 'Ticket Distribution by Priority (User)',
                  chartArea: { width: '50%' },
                  hAxis: {
                      title: 'Priority'
                  },
                  vAxis: {
                      title: 'Number of Tickets'
                  }
              };
             
          </c:if>

          chart = new google.visualization.ColumnChart(document.getElementById('column_chart'));
          chart.draw(data, options);

      }
      
      function paginate(tableId, paginationId, itemsPerPage) {
          const table = document.getElementById(tableId).getElementsByTagName('tbody')[0];
          const pagination = document.getElementById(paginationId);
          const rows = table.getElementsByTagName('tr');
          const numItems = rows.length;
          const numPages = Math.ceil(numItems / itemsPerPage);
          let currentPage = 1;

          if (numItems === 0) {
              const emptyRow = document.createElement('tr');
              const emptyCell = document.createElement('td');
              emptyCell.colSpan = 5;
              emptyCell.textContent = 'No Ticket data available';
              emptyCell.classList.add('empty-message');
              emptyRow.appendChild(emptyCell);
              table.appendChild(emptyRow);
          }

          function showPage(page) {
              currentPage = page;
              for (let i = 0; i < numItems; i++) {
                  rows[i].style.display = (i >= (page - 1) * itemsPerPage && i < page * itemsPerPage) ? '' : 'none';
              }
              updatePagination();
          }

          function updatePagination() {
              pagination.innerHTML = '';

              const prevButton = document.createElement('button');
              prevButton.textContent = 'Prev';
              prevButton.disabled = currentPage === 1;
              prevButton.onclick = () => showPage(currentPage - 1);
              pagination.appendChild(prevButton);

              for (let i = 1; i <= numPages; i++) {
                  const link = document.createElement('a');
                  link.innerHTML = i;
                  link.href = 'javascript:void(0)';
                  link.className = (i === currentPage) ? 'active' : '';
                  link.onclick = (function (page) {
                      return function () {
                          showPage(page);
                      };
                  })(i);
                  pagination.appendChild(link);
              }

              const nextButton = document.createElement('button');
              nextButton.textContent = 'Next';
              nextButton.disabled = currentPage === numPages;
              nextButton.onclick = () => showPage(currentPage + 1);
              pagination.appendChild(nextButton);
          }

          showPage(1);
      }

      document.addEventListener('DOMContentLoaded', function () {
          paginate('openTicketsTable', 'openPagination', 5);
          paginate('inProgressTicketsTable', 'inProgressPagination', 5);
          paginate('closedTicketsTable', 'closedPagination', 5);
      });
      
</script>



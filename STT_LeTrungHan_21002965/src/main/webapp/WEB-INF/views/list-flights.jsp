<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head><title>Airline Reservation System</title></head>
<body>
<h2>Airline Reservation System</h2>
<a href="FlightServlet?action=add">Add New Flight</a>
<c:forEach var="f" items="${flights}">
<div>
    ${f}
</div>
</c:forEach>
<table border="1" cellpadding="5">
    <tr>
        <th>Airplane Name</th><th>Departure</th><th>Arrival</th>
        <th>Departure Date</th><th>Arrival Date</th>
        <th>Seat Fare</th><th>Status</th><th>Action</th>
    </tr>
    <c:forEach var="f" items="${flights}">
        <tr>
            <td>${f.id}</td>
            <td>${f.getAirplane_name}</td>
            <td>${f.departure_airport}</td>
            <td>${f.arrival_Airport}</td>
            <td>${f.departure_date}</td>
            <td>${f.arrival_date}</td>
            <td>${f.seat_Fare}</td>
            <td>${f.flight_status}</td>
            <td><a href="FlightServlet?action=delete&id=${f.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

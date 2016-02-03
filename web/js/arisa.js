var endpoint = '../../webapp/api';


$('#personal').click(function() {
	console.log('start');
	$.ajax({
		url: endpoint + '/personal?name=2015046',
		dataType:'xml',
		success: function(xml) {
			console.log(xml);
			var acceptTicket = $(xml).find('acceptTicket');
			var id = $(acceptTicket).find('id');
			$('#result').append(id).text();
		},
		error: function(xhr, status, error) {
			console.log(error.message);
		}
	});
	console.log('end');
});
var endpoint = '../../webapp/api';
/*$('#last').click(*/
function prog() {
$.ajax({
		url: endpoint + '/top',
		dataType:'xml',
		success: function(xml) {

			var top = $(xml).find('top').each(function(){
// 重複するチケットは先に発見したチケットを取り出す、重複の扱いは完全に無視してる。
				$('#progress tbody *').remove();

				for(var j = 0; j < $(this).find('ownerList').size(); j++){
					var ownerlist = $(this).find('ownerList').eq(j).text();
					var UCname = "";
					var Ticket = "";
				for(var i = 0; i < $(this).find('eachAcceptTicket').find('acceptedTicket').size(); i++){
					var Owner = $(this).find('eachAcceptTicket').find('acceptedTicket').find('owner').eq(i).text();

					if(ownerlist == Owner){
					UCname = $(this).find('eachAcceptTicket').find('acceptedTicket').find('keywords').eq(i).text();
					Ticket = $(this).find('eachAcceptTicket').find('acceptedTicket').find('summary').eq(i).text();
					break;
						}
					}

					if(ownerlist != ""){
					$('#progress').append('<tbody>'+'<td>'+'<button type="button" class="dorakue_button" onclick="move(\''+ ownerlist +'\')">'+ownerlist+'</button>'
				+'</td>'+'<td>'+UCname+'</td>'+
				'<td>'+Ticket+'</td>'+'</tbody>');
					}
				}
			});
		}
	});
}
//);


/*$('#submit').click(*/
function history() {
// 重複するチケットは先に発見したチケットを取り出す、重複の扱いは完全に無視してる。
 $.ajax({
        url:'./TimelineRss.xml',
        dataType:'xml',
        timeout:1000,
        error:function() {
   			console.log("error");
        },
        success:function(xml){
			$('#timeline tbody *').remove();
			var channel = $(xml).find('channel').each(function(){
				for(var i =0; i < $(this).find('item').size(); i++){
				var title = $(this).find('item').find('title').eq(i).text();
				var creator = $(this).find('item').find('creator').eq(i).text();
				var pubDate = $(this).find('item').find('pubDate').eq(i).text();
				$('#timeline').append('<tbody>'+'<td>'+$(this).find('item').find('creator').eq(i).text()
				+'</td>'+'<td>'+$(this).find('item').find('title').eq(i).text()+'</td>'+
				'<td>'+$(this).find('item').find('pubDate').eq(i).text()+'</td>'+'</tbody>');
			}
			});
        }
    });
}
//);

function move(id){

	location.href = "./personal.html?" + escape(id);

}

function progressBar(){
$.ajax({
		url: endpoint + '/top',
		dataType:'xml',
		success: function(xml) {

			var top = $(xml).find('top').each(function(){
// 重複するチケットは先に発見したチケットを取り出す、重複の扱いは完全に無視してる。
				$('#progressbar tbody *').remove();

				var UCProgress = $(this).find('UCProgress');
				var totalProgress = $(this).find('totalProgress');
				$('#progressbar').append('<h4>'+ '全体の進行度' +'</h4>'+
				'<progress value="'+ totalProgress.text() +'" max="100"></progress>');

				for(var i = 0; i < UCProgress.size(); i++){
				var UCName =  $(UCProgress).find('UCName').eq(i).text();
				var progressRate =$(UCProgress).find('progressRate').eq(i).text();
				if(UCName != ""){
				$('#progressbar').append('<h5>'+ UCName +'</h5>'+
				'<progress value="'+ progressRate +'" max="100"></progress>');
				}
				console.log(UCName);
			}
			});
		}
	});

}

var endpoint = '../../webapp/api';
var ImgList = [];
var UCList = new Array();
var ctx = null;
function draw() {
$.ajax({
		url: endpoint + '/top',
		dataType:'xml',
		success: function(xml) {
			var top = $(xml).find('top').each(function(){

				var backWidth = 820;
				var backHeight = 600;
				var UCProgress = $(this).find('UCProgress');
				var totalProgress = $(this).find('totalProgress');
				totalProgress = Math.round(totalProgress.text());
				// var UCList = new Array();
				var ImgSourceList = [
					"./image/female-01.png",
					"./image/female-chara1-01.png",
					"./image/female-chara2.png",
					"./image/male-chara-01.png",
					"./image/male-chara-04.png",
				];
				UCList.push({"UCName":"全体","progressRate":totalProgress,"charaImg":0});
				var count = 1;
				for(var i = 0; i < UCProgress.size(); i++){
					var UCName =  $(UCProgress).find('UCName').eq(i).text();
					var progressRate =$(UCProgress).find('progressRate').eq(i).text();
					progressRate = Math.round(progressRate);
					if(UCName != ""){
						// UCList.push({"UCName":UCName,"progressRate":Math.round(progressRate),"charaImg":(i%(ImgSourceList.length-1))});
						UCList.push({"UCName":UCName,"progressRate":Math.round(progressRate),"charaImg":count});
						count++;
						console.log((i%(ImgSourceList.length-1)));
						console.log(ImgSourceList.length-1);
					}
				}
				// var ImgList = [];
				for(var i in ImgSourceList){
					ImgList[i] = new Image();
					ImgList[i].src = ImgSourceList[i];
				}
				var canvas = document.getElementById('canvas');
				if ( ! canvas || ! canvas.getContext ) { return false; }
				// var ctx = canvas.getContext('2d');
				ctx = canvas.getContext('2d');
				var loadedCount = 1;
				for(var i in ImgList){
					ImgList[i].addEventListener('load',function(){
						if(loadedCount == ImgList.length){
							setTimeout(
								function(backWidth,backHeight,rectCount) {
									drawCanvas(backWidth,backHeight,rectCount);
								}, 10, backWidth,backHeight,0 );
								// function(ctx,backWidth,backHeight,UCList,ImgList,rectCount) {
									// drawCanvas(ctx,backWidth,backHeight,UCList,ImgList,rectCount);
								// }, 10, ctx,backWidth,backHeight,UCList,ImgList,0 );
							// (function(backWidth,backHeight,UCList,ImgList){
								// for(var i = 0; i < UCList.length; i++){
									// var imgX = backWidth * (UCList[i].progressRate/100);
									// var imgY = backHeight * ((30+i*3)/100)+120;
									// if(i%2!=0){
										// imgY = backHeight * ((30-(i-0)*3)/100)+120;
									// }
									// var imgHeight = ImgList[UCList[i].charaImg].height;
									// var imgWidth = ImgList[UCList[i].charaImg].width;
									// // ctx.drawImage(ImgList[UCList[i].charaImg],backWidth * (UCList[i].progressRate/100),backHeight * ((25+i*5)/100));
									// ctx.drawImage(ImgList[UCList[i].charaImg],0,0,imgWidth,imgHeight,imgX,imgY,imgWidth / 2,imgHeight / 2);
									// var text = UCList[i].UCName+" "+UCList[i].progressRate+"%";
									// ctx.fillStyle = 'rgb(0, 0, 0)';
									// ctx.font = "20px 'MS ゴシック'";
									// var txw = ctx.measureText(text);
									// // ctx.fillRect(backWidth * ((UCList[i].progressRate+7)/100)-5,backHeight * ((28+i*5)/100)+9,txw.width+7,-30);
									// ctx.fillRect(imgX-3+10,imgY+5,txw.width+6,-30);
									// console.log(text);
									// console.log(txw.width);
									// ctx.fillStyle = "rgb(255, 255, 255)";
									// // ctx.fillText(text,backWidth * ((UCList[i].progressRate+7)/100),backHeight * ((28+i*5)/100), 200);
									// ctx.fillText(text,imgX+10,imgY-2,200);
								// }
							// })(backWidth,backHeight,UCList,ImgList);
						}
						loadedCount++;
					},false);
				}
//				var loadedCount = 1;
//				for(var i = 0; i < UCList.length; i++){
//					ctx.drawImage(UCList[i].charaImg,backWidth * (UCList[i].progressRate/100),backHeight * ((25+i*5)/100));
//					console.log(UCList[i].progressRate);
//					ctx.font = "20px 'MS ゴシック'";
//					ctx.fillStyle = "rgb(255, 255, 255)";
//					ctx.fillText(UCList[i].UCName+" "+UCList[i].progressRate+"%",backWidth * ((UCList[i].progressRate+7)/100),backHeight * ((25+i*5)/100), 200);
//				}
			});
		}
	});
}

// function drawCanvas(ctx,backWidth,backHeight,UCList,ImgList,rectCount){
function drawCanvas(backWidth,backHeight,rectCount){
	if(rectCount > 100){
		return;
	}
	console.log(rectCount);
	ctx.clearRect (0 , 0 , 1024 , 600);
	for(var i = 0; i < UCList.length; i++){
		// var imgX = backWidth * (UCList[i].progressRate/100);
		// var delta = backWidth * (UCList[i].progressRate/100) / 10;
		var imgX = 0;
		if(rectCount < UCList[i].progressRate){
			imgX = backWidth * (rectCount/100);
		}else{
			imgX = backWidth * (UCList[i].progressRate/100);
		}
		var imgY = backHeight * ((30+i*1)/100)+120;
		if(i%2!=0){
			imgY = backHeight * ((30-i*1)/100)+120;
		}
		// console.log(UCList[i].charaImg);
		// console.log(ImgList[UCList[i].charaImg]);
		var imgHeight = ImgList[UCList[i].charaImg].height;
		var imgWidth = ImgList[UCList[i].charaImg].width;
		// ctx.drawImage(ImgList[UCList[i].charaImg],backWidth * (UCList[i].progressRate/100),backHeight * ((25+i*5)/100));
		ctx.drawImage(ImgList[UCList[i].charaImg],0,0,imgWidth,imgHeight,imgX,imgY,imgWidth / 2,imgHeight / 2);
		var text = UCList[i].UCName+" "+UCList[i].progressRate+"%";
		if(rectCount < UCList[i].progressRate){
			text = UCList[i].UCName+" "+rectCount+"%";
		}
		// ctx.fillStyle = 'rgb(0, 0, 0)';
		ctx.fillStyle = 'rgb(255, 0, 0)';
		ctx.font = "20px 'dorakue'";
		var txw = ctx.measureText(text);
		// ctx.fillRect(backWidth * ((UCList[i].progressRate+7)/100)-5,backHeight * ((28+i*5)/100)+9,txw.width+7,-30);
		ctx.fillRect(imgX-3+10-2,imgY+5+2,txw.width+6+4,-30-4);
		ctx.fillStyle = 'rgb(0, 0, 0)';
		ctx.fillRect(imgX-3+10,imgY+5,txw.width+6,-30);
		// console.log(text);
		// console.log(txw.width);
		ctx.fillStyle = "rgb(255, 255, 255)";
		// ctx.fillText(text,backWidth * ((UCList[i].progressRate+7)/100),backHeight * ((28+i*5)/100), 200);
		ctx.fillText(text,imgX+10,imgY-2,200);
	}
	rectCount += 1;
	setTimeout(
		function(backWidth,backHeight,rectCount) {
			drawCanvas(backWidth,backHeight,rectCount);
		}, 20, backWidth,backHeight,rectCount );
		// function(ctx,backWidth,backHeight,UCList,ImgList,rectCount) {
			// drawCanvas(ctx,backWidth,backHeight,UCList,ImgList,rectCount);
		// }, 10, ctx,backWidth,backHeight,UCList,ImgList,rectCount );
}

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
					$('#progress').append('<tbody>'+'<td class="button_margin">'+'<div class="form-group"><button type="button" class="dorakue_button button_width" onclick="move(\''+ ownerlist +'\')">'+ownerlist+'</button></div>'
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
				'<div class="progress progress-striped active">'+
				'<div class="progress-bar" style="width: '+ totalProgress.text() +'%"></div></div>');

				for(var i = 0; i < UCProgress.size(); i++){
				var UCName =  $(UCProgress).find('UCName').eq(i).text();
				var progressRate =$(UCProgress).find('progressRate').eq(i).text();
				if(UCName != ""){
				$('#progressbar').append('<h5>'+ UCName +'</h5>'+
				'<div class="progress progress-striped active">'+
				'<div class="progress-bar" style="width: '+ progressRate +'%"></div></div>');
				}
				console.log(UCName);
			}
			});
		}
	});

}
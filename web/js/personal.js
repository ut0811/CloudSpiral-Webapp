var endpoint = '../../webapp/api';

var receive = location.search.substring(1,location.search.length);
var owner = unescape(receive);

//受注チケットの項目リスト
var acceptticketTableItemList =
[
	"id",
	"summary",
	"status",
	"type",
	"component",
	"estimatehours",
	"startdate",
	"keywords",
	"cc"
];
//完了チケットの項目リスト
var fixedticketTableItemList =
[
	"id",
	"summary",
	"status",
	"type",
	"milestone",
	"component",
	"estimatehours",
	"startdate",
	"totalhours",
	"enddate",
	"keywords",
	"cc"
];
//チケット項目の名前
var itemName ={
	"id":"id",
	"summary":"概要",
	"status":"状態",
	"owner":"所有者",
	"type":"タイプ",
	"milestone":"マイルストーン",
	"component":"コンポーネント",
	"resolution":"レゾリューション",
	"time":"時間",
	"changetime":"変更時間",
	"estimatehours":"見積もり",
	"startdate":"開始日時",
	"totalhours":"合計時間",
	"enddate":"終了日時",
	"keywords":"UC",
	"cc":"関係者"
};
console.log('start');
// チケット系のテーブルの項目名を設定
var acceptTableHeadString = "<tr class=\"info\">";
for(var i in acceptticketTableItemList){
	acceptTableHeadString += "<th><div id=\"acceptticketitemName"+i+"\"></div></th>";
}
acceptTableHeadString += "</tr>";
// チケット系のテーブルの項目名を設定
var fixedTableHeadString = "<tr class=\"info\">";
for(var i in fixedticketTableItemList){
	fixedTableHeadString += "<th><div id=\"fixedticketitemName"+i+"\"></div></th>";
}
fixedTableHeadString += "</tr>";
// 『受注』チケットのヘッダーの設定
$('#acceptTicketTableHead').append(acceptTableHeadString).text();
// 『完了』チケットのヘッダーの設定
$('#fixedTicketTableHead').append(fixedTableHeadString).text();
// REST呼び出し
$.ajax({
	url: endpoint + '/personal?name='+owner,
	dataType:'xml',
	success: function(xml) {
		console.log(xml);
		// 『受注』チケットの表示
		displayAcceptTicket(xml);
		// 『完了』チケットの表示
		displayFixedTicket(xml);
		// 『ログ』の表示
		displayTicketLog(owner);
		// 『達人ランク』の表示
		displayTatujinRank(xml);
		// 『サイドバー』の表示
		displaySideBar(xml,owner);
		// 『ミスチケット』の表示
		displayMissTicket(xml);
		// 『バッジ』の表示
		displayBatch(xml);
	},
	error: function(xhr, status, error) {
		console.log(error.message);
	}
});
console.log(acceptTableHeadString);
for(var i in acceptticketTableItemList){
	// $('#acceptticketitemName'+i).append(itemName[acceptticketTableItemList[i]]).text();
	$('#acceptticketitemName'+i).append(acceptticketTableItemList[i]).text();
}
for(var i in fixedticketTableItemList){
	// $('#fixedticketitemName'+i).append(itemName[fixedticketTableItemList[i]]).text();
	$('#fixedticketitemName'+i).append(fixedticketTableItemList[i]).text();
}
console.log('end');

//受注チケット表示関数
function displayAcceptTicket(xml){
	var Count = 0;
	$(xml).find('acceptTicket').each(function(){
		var acceptTicketList = new Array();
		for(var i in acceptticketTableItemList){
			acceptTicketList.push($(this).find(acceptticketTableItemList[i]).text());
		}
		var TableString = "<tr>";
		for(var i in acceptticketTableItemList){
			TableString += "<td><div id=\"acceptticket"+Count+"item"+i+"\"></div></td>";
		}
		TableString += "</tr>";
		$('#acceptTicketTableBody').append(TableString).text();
		for(var i in acceptticketTableItemList){
			$('#acceptticket'+Count+'item'+i).append(acceptTicketList[i]).text();
		}
		Count++;
	});
}
// 『達成』チケット表示関数
function displayFixedTicket(xml){
	var Count = 0;
	$(xml).find('fixedTicket').each(function(){
		var fixedTicketList = new Array();
		for(var i in fixedticketTableItemList){
			fixedTicketList.push($(this).find(fixedticketTableItemList[i]).text());
		}
		var TableString = "<tr>";
		for(var i in fixedticketTableItemList){
			TableString += "<td><div id=\"fixedticket"+Count+"item"+i+"\"></div></td>";
		}
		TableString += "</tr>";
		$('#fixedTicketTableBody').append(TableString).text();
		for(var i in fixedticketTableItemList){
			$('#fixedticket'+Count+'item'+i).append(fixedTicketList[i]).text();
		}
		Count++;
	});
}

//『ログ』表示関数
function displayTicketLog(owner){
	$.ajax({
		url:'./TimelineRss.xml',
		dataType:'xml',
		timeout:1000,
		error:function() {
			console.log("error");
		},
		success:function(xml){
			$('#TicketLogTitle').append(owner+"が関連しているチケット");
			$('#logBoxHead').append("<tr class=\"info\">"+"<td>"+"タイムライン"+"</td>"+"</tr>");
			var channel = $(xml).find('channel').each(function(){
				for(var i =0; i < $(this).find('item').size(); i++){
					var title = $(this).find('item').find('title').eq(i).text();
					var creator = $(this).find('item').find('creator').eq(i).text();
					var pubDate = $(this).find('item').find('pubDate').eq(i).text();
					if(creator == owner){
						$('#logBoxBody').append("<tr>"+"<td>"+$(this).find('item').find('creator').eq(i).text()+'が'+$(this).find('item').find('title').eq(i).text()+','+$(this).find('item').find('pubDate').eq(i).text()+"</td>"+"</tr>");
					}
				}
			});
		}
	});
}
// 『達人ランク』の表示関数
function displayTatujinRank(xml){
	var MeanEstErr = calcurateMeanEstErr(xml);
	var Rank = calcurateTatujinRank(MeanEstErr);
	$('#tatujinRank').append("達人ランク: "+Rank).text();
	$('#MeanEstErr').append("見積もり誤差平均: "+MeanEstErr + " %").text();
}
// 平均見積もり誤差を計算する関数
function calcurateMeanEstErr(xml){
	var MeanEstErr = 0;
	var Count = 0;
	$(xml).find('estErrList').each(function(){
		var estErr = $(this).find('estErr').text();
		estErr = estErr - 0;
		MeanEstErr = MeanEstErr + estErr;
		Count++;
	});
	return Math.round(MeanEstErr / Count);
}
// 達人ランクを計算する関数
function calcurateTatujinRank(MeanEstErr){
	var Rank = "";
	if(MeanEstErr > 300){
		Rank = "F";
	}else if(MeanEstErr > 240){
		Rank = "E";
	}else if(MeanEstErr > 180){
		Rank = "D";
	}else if(MeanEstErr > 120){
		Rank = "C";
	}else if(MeanEstErr > 60){
		Rank = "B";
	}else if(MeanEstErr > 30){
		Rank = "A";
	}else{
		Rank = "S";
	}
	return Rank;
}

// サイドバーの表示関数
function displaySideBar(xml,owner){
	$('#chara-img').attr('src',"./image/female-01.png");
	$('#personalName').append("Name: "+owner).text();
	console.log(owner);
	var TotalExp = $(xml).find('exp').text();
	$('#TotalExp').append("獲得Exp: "+TotalExp).text();
	const condition = 100;//condition×Lvがレベルアップに必要な経験値
	var Lv = calcurateLv(TotalExp,condition);
	$('#Lv').append("Lv: "+Lv).text();
	var NextExp = calcurateNextExp(TotalExp,condition);
	$('#NextExp').append("次のレベルまで<br>Exp: "+NextExp).text();
	var Rank = calcurateTatujinRank(calcurateMeanEstErr(xml));
	$('#Rank').append("達人ランク: "+Rank).text();

	var Gold = 1000;
	$('#Gold').append("Gold: "+Gold).text();
	// var Batch = $(xml).find('batchList').length;
	var Batch = 3;
	$('#Batch').append("バッチ: "+Batch).text();

}

// レベルの計算関数
function calcurateLv(Exp,condition){
	Exp = Exp - 0;//Expをintにするつもり
	var Lv=1;
	while(Exp > 0){
		if(Exp > Lv * condition){
			Exp -= Lv * condition;
			Lv++;
		}else{
			break;
		}
	}
	return Lv;
}
// 次のレベルまでのExpの計算関数
function calcurateNextExp(Exp,condition){
	Exp = Exp - 0;//Expをintにするつもり
	NextExp = 0;//次のレベルアップに必要な経験値
	count = 1;
	while(Exp > NextExp){
		NextExp += count * condition;
		count++;
	}
	return NextExp - Exp;
}

// ミスチケットの表示関数
function displayMissTicket(xml){
	if($(xml).find('errList').length){
		// エラーがある場合
		var AlertWindow ="<div id = \"ErrListWindow\" class=\"alert alert-info\">"
						+"<button class=\"close\" data-dismiss=\"alert\">&times;</button>"
						+"</div>";
		$('#MissTicketInfo').append(AlertWindow).text();
		$(xml).find('errList').each(function(){
			var ErrMsg = $(this).find('errMsg').text();
			var ticketName = "";
			$(this).find('ticketName').each(function(i,elem){
				ticketName += $(elem).text() + " , ";
			});
			ticketName = ticketName.substring(0,ticketName.length-3);
			$('#ErrListWindow').append("<p>エラー："+ErrMsg+"</p><p>関連するチケット:"+ticketName+"</p>").text();
		});
	}else{
		// エラーがない場合
	}
}
// バッジの表示関数
function displayBatch(xml){
	var gold = "<img class=\"batchimg\" src=\"./image/gold.png\"></img>";
	var silber = "<img class=\"batchimg\" src=\"./image/silber.png\"></img>";
	var copper = "<img class=\"batchimg\" src=\"./image/copper.png\"></img>";
	// if($(xml).find('batchList').length){
		// var index = 0;
		// $(xml).find('batchList').each(function(){
			// var BatchName = $(this).text();
			// if(index % 3 == 0){
				// $('#BatchDisplay').append(gold+BatchName).text();
			// }else if(index % 3 == 1){
				// $('#BatchDisplay').append(silber+BatchName).text();
			// }else{
				// $('#BatchDisplay').append(copper+BatchName).text();
			// }
			// index++;
		// });
	// }
	$('#BatchDisplay').append(gold).text();
	$('#BatchDisplay').append(silber).text();
	$('#BatchDisplay').append(copper).text();
}

function move(id){

	location.href = "./personal.html?" + escape(id);

}

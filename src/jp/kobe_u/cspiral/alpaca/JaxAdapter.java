package jp.kobe_u.cspiral.alpaca;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import jp.kobe_u.cspiral.alpaca.model.Personal;
import jp.kobe_u.cspiral.alpaca.model.PhotoIdList;
import jp.kobe_u.cspiral.alpaca.model.Top;
import jp.kobe_u.cspiral.alpaca.model.TracLog;

@Path("/")
public class JaxAdapter {

	private final AlpacaController controller = new AlpacaController();
	private final PersonalController personalController = new PersonalController();
	private final TopController topController = new TopController();
	private final GetTicketInfoController getTicketInfoController = new GetTicketInfoController();

	/**
	 * いいねを投稿する
	 * @return okだけ
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/like")
	public Response like() {
		controller.like();
		return Response.status(200).entity("<like>ok</like>").build();
	}

	/**
	 * コメントする
	 * @param message コメント本文
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML})
	@Path("/comment")
	public Response comment(@QueryParam("msg") final String message) {
		if ("".equals(message)) {
			return Response.status(403).entity("<comment>error</comment>").build();
		}
		controller.comment(message);
		return Response.status(200).entity("<comment>ok</comment>").build();
	}

	/**
	 * コメント一覧といいね回数を取得
	 * @param n コメント数（デフォルトは20）
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/report")
	public Response getReport(
			@DefaultValue("20") @QueryParam("n") final int n) {
		if (n < 0) {
			return Response.status(403).entity("<comment>error</comment>").build();
		}
		return Response.status(200).entity(controller.getReport(n)).build();
	}

	/**
	 * 写真をアップロードする
	 * POSTメソッドなのでブラウザのURL入力バーから直接呼べないことに注意
	 * @param photoData 写真データ（MIMEでシリアライズされた画像データ）
	 * @return 画像ID
	 */
	@POST
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/photo")
	public Response uploadPhoto(
			@FormParam("src") String photoData
			) {
		String photoId = controller.savePhoto(photoData);
		return Response.status(200).entity("<photo_id>" + photoId + "</photo_id>").build();
	}

	/**
	 * 撮影した画像ファイルを返す
	 * @param 写真のid
	 * @return png画像
	 */
	@GET
	@Produces("image/png")
	@Path("/photo/{id}.png")
	public Response getPhoto(
			@PathParam("id") String id) {
		ByteArrayOutputStream baos = controller.getPhoto(id);
		if (baos == null) {
			return Response.status(403).entity("<error>photo not found</error>").build();
		}
		byte[] photoData = baos.toByteArray();
		return Response.ok(new ByteArrayInputStream(photoData)).build();
	}


	/**
	 * 撮影した写真のIDのリストを返す
	 * @param n (写真の枚数，デフォルトは40）
	 * @return 写真IDリスト
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/photo/list")
	public Response getPhotoList(
			@DefaultValue("40") @QueryParam("n") final int n
			) {
		List<String> list = controller.getPhotoList(n);
		PhotoIdList pil = new PhotoIdList(list);
		return Response.ok(pil).build();
	}

	/**
	 * ./api/ へのアクセスを ./api/application.wadl（APIの仕様書） にリダイレクトする
	 * @return
	 * @throws URISyntaxException
	 */
	@GET
	@Path("/")
	public Response redirect() throws URISyntaxException{
		URI uri = new URI("application.wadl");
		return Response.seeOther(uri).build();
	}

	/**
	 * 色々試す用のサンプル
	 * @param
	 * @return
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/sample")
	public Response getSample(
//			@QueryParam("name") final String userId,
//			@QueryParam("pass") final String password
			) {
//		return Response.ok("ok").build();
		return Response.status(200).entity("<sample>"+personalController.calcurateBatch(personalController.findByOwnerAndFixed("2015046"))+"</sample>").build();
//		return Response.status(200).entity(personalController.calcurateBatch(personalController.findByOwnerAndFixed("yuta")).size()).build();
//		return Response.status(200).entity(personalController.findByOwnerAndFixed("yuta").size()).build();
//		return Response.status(200).entity(String.valueOf("<sample>" + personalController.findByOwnerAndAccepted("2015046").size())+"</sample>").build();
//		return Response.status(200).entity("<sample>" + getTicketInfoController.TicketInfoInsertDBfromURL(userId, password,"http://ec2-52-69-42-30.ap-northeast-1.compute.amazonaws.com/trac/query?status=accepted&status=assigned&status=closed&status=new&status=reopened&format=tab&col=id&col=summary&col=status&col=type&col=priority&col=milestone&col=component&col=version&col=resolution&col=time&col=changetime&col=estimatedhours&col=startdate&col=totalhours&col=enddate&col=reporter&col=keywords&col=cc&col=owner&report=18&order=priority")+"</sample>").build();
//		return Response.status(200).entity("<sample>" + topController.calcTotalProgress()+"</sample>").build();
	}


	/**
	 * 個人ページの情報を取得する
	 * @param onwerName 名前
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Produces(MediaType.APPLICATION_JSON)
	@Path("/personal")
	public Response personal(@QueryParam("name") final String ownerName){
		if ("".equals(ownerName)) {
			return Response.status(403).entity("<personal>error</personal>").build();
		}
		Personal personal = new Personal();
		personal.setAcceptTicket(personalController.findByOwnerAndAccepted(ownerName));
		personal.setFixedTicket(personalController.findByOwnerAndFixed(ownerName));
		personal.setEstErrList(personalController.calculateEstErr(personal.getFixedTicket()));
		personal.setErrList(personalController.calculateErr(personal.getAcceptTicket(),personal.getFixedTicket()));
		personal.setExp(personalController.calcurateExp(personal.getFixedTicket()));
		List<TracLog> temp = personalController.findTracLogByName(ownerName);
//		List<String> TracLog = new ArrayList<String>();
//		for(int i = 0;i<temp.size();i++){
//			TracLog.add(temp.get(i).getCreater()+"が"+temp.get(i).getTitle());
//		}
//		personal.setTracLog(TracLog);
//		personal.setTracLog(personalController.calcurateBatch(personal.getFixedTicket()));
		personal.setBatchList(personalController.calcurateBatch(personal.getFixedTicket()));
		return Response.ok(personal).build();
	}

	/**
	 * トップページの情報を取得する
	 * @param onwerName 名前
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	@Path("/top")
	public Response top(){
		Top top = new Top();
		top.setTotalProgress(topController.calcTotalProgress());
		top.setUCProgress(topController.calcUCProgress());
		top.setOwnerList(topController.findOwnerList());
		top.setEachAcceptTicket(topController.findByAccepted());
		/*
		List<TracLog> temp = topController.findTracLogByName(ownerName);
		List<String> TracLog = new ArrayList<String>();
		for(int i = 0;i<temp.size();i++){
			TracLog.add(temp.get(i).getCreater()+"が"+temp.get(i).getTitle());
		}
		topsonal.setTracLog(TracLog);
		*/

		return Response.ok(top).build();
	}

	/**
	 * TICKETコレクションを最新の状態にする
	 * @param userId password urlString (TracにログインするID,Password,URL)
	 * @return succeceful(userId) もしくは エラーメッセージ
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response update(){
//		public Response update(@QueryParam("userId") final String userId,@QueryParam("password") final String password,@QueryParam("url") final String urlString){
		String userId = "y-furuta";
		String password = "1234";
		String urlString = "http://ec2-52-69-42-30.ap-northeast-1.compute.amazonaws.com/trac/query?status=accepted&status=assigned&status=closed&status=new&status=reopened&format=tab&col=id&col=summary&col=status&col=type&col=priority&col=milestone&col=component&col=version&col=resolution&col=time&col=changetime&col=estimatedhours&col=startdate&col=totalhours&col=enddate&col=reporter&col=keywords&col=cc&col=owner&report=18&order=priority";
		String resultString = getTicketInfoController.TicketInfoInsertDBfromURL(userId, password, urlString);

		return Response.status(200).entity("<update>" + resultString +"</update>").build();
	}


}

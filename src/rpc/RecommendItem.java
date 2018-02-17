package rpc;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import algorithm.GeoRecommendation;
import entity.Item;

/**
 * Servlet implementation class RecommendItem
 */
@WebServlet("/recommendation")
public class RecommendItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.setContentType("application/json");
		
//		PrintWriter out = response.getWriter();
//		
//		JSONArray arr = new JSONArray();
//		JSONObject obj1 = new JSONObject();
//		JSONObject obj2 = new JSONObject();
//
//		try {
//			obj1.put("name", "abcd");
//			obj1.put("address", "San Francisco");
//			obj1.put("time", "01/01/2017");
//			
//			obj2.put("name", "1234");
//			obj2.put("address", "San Jose");
//			obj2.put("time", "01/02/2017");
//			
//			arr.put(obj1);
//			arr.put(obj2);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		out.print(arr);
//		out.flush();
//		out.close();
		
//		JSONArray array = new JSONArray();
//		try {
//			array.put(new JSONObject().put("name", "abcd").put("address", "san francisco").put("time", "01/01/2017"));
//			array.put(new JSONObject().put("name", "1234").put("address", "san jose").put("time", "01/02/2017"));
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		RpcHelper.writeJsonArray(response, array);
		
		// allow access only if session exists
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.setStatus(403);
			return;
		}
		String userId = session.getAttribute("user_id").toString();
		
//		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		GeoRecommendation recommendation = new GeoRecommendation();
		List<Item> items = recommendation.recommendItems(userId, lat, lon);

		JSONArray result = new JSONArray();
		try {
			for (Item item : items) {
				result.put(item.toJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, result); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.gra.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gra.entity.Comment;
import com.gra.entity.InfoReplies;
import com.gra.entity.Song;
import com.gra.service.ISongService;
import com.gra.form.*;
import com.gra.service.ICommentService;

	/**
	 * 歌曲管理控制器
	 * 
	 * @author susie
	 *
	 */
@Controller
@RequestMapping(value = "/song")
public class SongController {
	
	
		@Autowired
		private ISongService songServiceImpl;
		
		@Autowired
		private ICommentService commentserviceImpl;

		//获取日志对象
		private static Logger logger = Logger.getLogger(SongController.class);
		
		/**
		 * 显示主页歌曲
		 * 
		 * 
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/lists", method = RequestMethod.POST)
		public Map<String, Object> getSong() {			
			// 调用Service层的查询方法
			List<Song> songs = songServiceImpl.getAllSongs();
			Map<String, Object> info = new HashMap<String, Object>();			
			info.put("song", songs);
			return info;
		}
		
		/**
		 * 热歌榜
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/hotsong",method = RequestMethod.POST)
		public Map<String, Object> getHotSong(){
			List<Song> songs = songServiceImpl.getSongByTups();
			Map<String,Object>  info = new HashMap<String,Object>();
			info.put("hotSong",songs);
			return info;
		}
		
		/**
		 * 新歌榜
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/newsong",method = RequestMethod.GET)
		public Map<String,Object> getNewSong(){
			List<Song> songs = songServiceImpl.getSongById();
			Map<String,Object> info = new HashMap<String,Object>();
			info.put("newSong", songs);
			return info;
		}

		/**
		 * 根据歌名模糊查询
		 * 
		 * @param type
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/title", method = RequestMethod.POST)
		public Map<String,Object> getSongByTitle(String title) {
			logger.info("==>indistinct info of title:" + title);
			System.out.println(title);
			// 调用Service层的查询方法
			List<Song> songs = songServiceImpl.getSongByTitle(title);			
			Map<String,Object> info = new HashMap<String,Object>();
			info.put("querySong", songs);
			return info;			
		}

		/**
		 * 根据歌手名确切查询
		 * @param artist
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/artist", method = RequestMethod.POST)
		public List<Song> getSongByArtist(String artist){
			
			// 调用Service层的查询方法
			List<Song> songs = songServiceImpl.getSongByArtist(artist);			
			if (songs.size() != 0) {
				for (Song song : songs) {
					songs.add(song);
				}
			}
			return songs;
		}	
		
		/**
		 * 显示主题歌曲页面
		 * @param kind
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value = "/kind", method = RequestMethod.GET)
		public Map<String,Object> getSongByKind(String kind){
			logger.info("==>get songs by kind:" + kind);
			System.out.println(kind);
			// 调用Service层的查询方法
			List<Song> songs = songServiceImpl.getSongByKind(kind);			
			Map<String,Object> info = new HashMap<String,Object>();
			info.put("kindSong", songs);
			return info;	
		}

		/**
		 * 新增歌曲
		 * 
		 * @param song
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/addsong")
		public String addsong(SongForm songForm) {
			logger.info("==>addsong info of the songForm:" + songForm.toString());
			// 由songForm转化成song
			Song song = new Song(songForm.getTitle(),songForm.getMp3(),songForm.getPoster(),songForm.getArtist(),songForm.getLrc(),songForm.getS_kind());
			// 调用Service层的查询方法，返回添加成功与否标识
			boolean isAdded = songServiceImpl.addSong(song);
			if (isAdded) {
				return "AddsongSuccess";
			} else {
				return "AddsongFailed";
			}
		}

		/**
		 * 添加评论
		 * 
		 * @param reply
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/addComment")
		public String addReply(CommentForm commentForm) {
			logger.info("==>addComment info of the replyForm:" + commentForm.toString());
			// 由CommentForm转化成Comment
			Comment comment = new Comment(commentForm.getS_id(),commentForm.getU_id(),commentForm.getC_details(),commentForm.getUsername());
			// 调用Service层的查询方法，返回更新成功与否标识
			boolean isAddReply = commentserviceImpl.addComment(comment);
			if (isAddReply) {
				return "AddReplySuccess";
			} else {
				return "AddReplyFailed";
			}
		}
		/**
		 * 获取当前歌曲所有评论
		 * @param s_id
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/getComment")
		public Map<String,Object> getAllComment(Integer s_id){
			List<Comment> comment = commentserviceImpl.getAllComments(s_id);
			Map<String,Object> replay = new HashMap<String,Object>();
			replay.put("replies", comment);
			return replay;
		}
		/**
		 * 更新歌曲点赞数
		 * @param s_id
		 * @param className
		 * @return
		 */
		@ResponseBody
		@RequestMapping(value="/addTup",method = RequestMethod.POST)
		public String updateTups(Integer s_id,String className){
			Song song = songServiceImpl.getSongById(s_id);
			Integer s_tup=0;
			if(className.equals("pull-right active")){
				 s_tup = song.getS_tup()-1;	
			}
			else if(className.equals("pull-right")){
				s_tup = song.getS_tup()+1;
			}
			song.setS_tup(s_tup);
			boolean isupdate = songServiceImpl.updateSongTups(song);
			if(isupdate){
				return "UpdateSuccess";
			}else{
				return "UpdateFail";
			}
		}
		/**
		 * 上传音乐
		 * @param request
		 * @param song
		 * @param file
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping(value="/upsong",method = RequestMethod.POST,produces={"text/html;charset=UTF-8;","application/json;"})	
		public String upsong(@RequestParam("file") MultipartFile file,HttpServletRequest request,Song song)throws Exception{			
			//如果文件不为空，写入上传路径
	        if(!file.isEmpty()) {
	            //上传文件路径
	            String path = request.getServletContext().getRealPath("/music/");
	            System.out.println(path);
	            //上传文件名
	            String filename = file.getOriginalFilename();
	            System.out.println(filename);
	            File filepath = new File(path,filename);
	            Integer i = (int) (Math.random() * 10+1);
	            song.setLrc("../lrc/"+filename+".txt");
	            song.setMp3("../music/"+filename);
	            song.setPoster("../image/p"+i+".jpg");
	            song.setS_comment(0);
	            song.setS_download(0);
	            song.setS_hears(0);
	            song.setS_tup(0);
				songServiceImpl.addSong(song);
	            //判断路径是否存在，如果不存在就创建一个
	            if (!filepath.getParentFile().exists()) { 
	                filepath.getParentFile().mkdirs();
	            }
	            //将上传文件保存到一个目标文件当中
	            file.transferTo(new File(path + File.separator + filename));
	            return "success";
	        } else {
	            return "error";
	        }
		}
		/**
		 * 下载歌曲
		 * @param request
		 * @param filename
		 * @param model
		 * @return
		 * @throws Exception
		 */
		@ResponseBody
		@RequestMapping(value="/download")
	     public ResponseEntity<byte[]> download(HttpServletRequest request,
	             String filename,
	             Model model)throws Exception {
	        //下载文件路径
	        String path = request.getServletContext().getRealPath("/music/");
	        File file = new File(path + File.separator + filename);
	        HttpHeaders headers = new HttpHeaders();  
	        //下载显示的文件名，解决中文名称乱码问题  
	        String downloadFielName = filename;//new String(filename.getBytes("UTF-8"),"iso-8859-1");
	        //通知浏览器以attachment（下载方式）打开图片
	        headers.setContentDispositionFormData("attachment", downloadFielName); 
	        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
	                headers, HttpStatus.CREATED);  
	     }
	     /**
	      * 根据歌曲id获取歌曲
	      */
	    @ResponseBody
		@RequestMapping(value="/songid")
	    public Song getSongById(Integer s_id){
	    	Song song = songServiceImpl.getSongById(s_id);
	    	return song;
	    }
	     
}

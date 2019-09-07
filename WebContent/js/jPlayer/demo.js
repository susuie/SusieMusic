

var song = null;
$(document).ready(function(){
	$.ajax({
		type:"post",
		url:"../song/lists",
		async:false,
		dataType:"json",
		success:function(data){
			song = data.song;
			//alert(data);
		},
		error:function(data){
			alert("获取数据失败！");
		}
	});
	var time="";
  var myPlaylist = new jPlayerPlaylist({
    jPlayer: "#jplayer_N",
    cssSelectorAncestor: "#jp_container_N"
  },song, {
    playlistOptions: {
      enableRemoveControls: true,
      autoPlay: false
    },
    swfPath: "js/jPlayer",
    supplied: "webmv, ogv, m4v, oga, mp3",
    smoothPlayBar: true,
    keyEnabled: true,
    audioFullScreen: false,
    
  });
  
  $(document).on($.jPlayer.event.pause, myPlaylist.cssSelector.jPlayer,  function(){
    $('.musicbar').removeClass('animate');
    $('.jp-play-me').removeClass('active');
    $('.jp-play-me').parent('li').removeClass('active');
  });

  $(document).on($.jPlayer.event.play, myPlaylist.cssSelector.jPlayer,  function(){
    $('.musicbar').addClass('animate');
  });

  $(document).on('click', '.jp-play-me', function(e){
    e && e.preventDefault();
    var $this = $(e.target);
    if (!$this.is('a')) $this = $this.closest('a');

    $('.jp-play-me').not($this).removeClass('active');
    $('.jp-play-me').parent('li').not($this.parent('li')).removeClass('active');

    $this.toggleClass('active');
    $this.parent('li').toggleClass('active');
    if( !$this.hasClass('active') ){
      myPlaylist.pause();
    }/*else{
      var i = Math.floor(Math.random() * (1 + 7 - 1));
      myPlaylist.play(i);
    }*/
    
  });



  // video

  $("#jplayer_1").jPlayer({
    ready: function () {
      $(this).jPlayer("setMedia", {
        title: "Big Buck Bunny",
        m4v: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.m4v",
        ogv: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.ogv",
        webmv: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.webm",
        poster: "../image/m41.jpg"
      });
    },
    swfPath: "js",
    supplied: "webmv, ogv, m4v",
    size: {
      width: "100%",
      height: "auto",
      cssClass: "jp-video-360p"
    },
    globalVolume: true,
    smoothPlayBar: true,
    keyEnabled: true
  });

});
function playMusic(id){
	var myPlaylist = new jPlayerPlaylist({
	    jPlayer: "#jplayer_N",
	    cssSelectorAncestor: "#jp_container_N"
	  },song, {
	    playlistOptions: {
	      enableRemoveControls: true,
	      autoPlay: false
	    },
	    swfPath: "js/jPlayer",
	    supplied: "webmv, ogv, m4v, oga, mp3",
	    smoothPlayBar: true,
	    keyEnabled: true,
	    audioFullScreen: false
	  });
	var i=0;
    for(i=0;i<song.length;i++){
      if(id==song[i].s_id){
        myPlaylist.play(i);
		break;
      }
    }
}
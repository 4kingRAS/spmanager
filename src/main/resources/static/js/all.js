//选项卡
function tab(tSel, wSel, sItemSel) {
    $(tSel + ' li').click(function () {
        var title = $(this).index();
        $(tSel + ' li a').removeClass('on');
        $(this).children('a').addClass('on');
        $(wSel + ' ' + sItemSel).hide().get(title).style.display = 'block';
    });
};

$(function () {
 
  //左侧菜单		
	$("#meau").on("click",'[data-role="root"]',function(){
	  var level= $(this).attr("data-level");
	  if($(this).parents('[data-tit="' + level + '"]').find('[data-con="' + level + '"]').css("display")=="block"){
		 $(this).parents('[data-tit="' + level + '"]').find('[data-con="' + level + '"]').hide();
		 $(this).removeClass("on");
		  }	
	  else{
		 $("#meau").find('[data-con="' + level + '"]').hide();		  
		 $(this).parents('[data-tit="' + level + '"]').siblings().find('[data-con="' + level + '"]').hide();
		 $(this).parents('[data-tit="' + level + '"]').find('[data-con="' + level + '"]').show();
		 $(this).parents('[data-tit="' + level + '"]').siblings().find("a").removeClass("on");
		 $(this).addClass("on");		 
		  }	 
  });
  
  $("#meau > ul > li:not(:has(div))").find('[data-role="root"]').addClass("have");
	 
	 $("#meau a").on("click",function(){
	   $("#m_left").removeClass("off");
	   $(".main").removeClass("on");	 
	   $(this).parent().siblings().find("a").removeClass("on");
	   $(this).addClass("on");
	 });

	 
	  $("#meau_btn").click(function(){
		if($("#m_left").hasClass("off")){
			  $("#m_left").removeClass("off");
			  $(".main").removeClass("on");
			}
		else{
			  $("#m_left").addClass("off");
			  $(".main").addClass("on");
			  $("#meau a").removeClass("on");
			  $('[data-role="tree_action"]').hide();
			}	
	  });	
});


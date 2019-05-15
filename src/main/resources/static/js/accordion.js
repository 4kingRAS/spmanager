(function($){

	$.fn.eKingAccordion = function(options){
		
		var options = $.extend({}, {
			activeClass		:'on',            //手风琴标题选中样式名
			speed			:0,          //滚动速度，'slow','normal','fast'，也可以用数值代替，比如2000表示2000毫秒
			openRow			:0                //展开第几条，默认为0不展开
		}, options);
		
		this.each(function() {
            var box=$(this);
		  
			$(box).find('[data-role="root"]').click(function(){
			  var level= $(this).attr("data-level");
			  if($(this).parent().find('[data-con="' + level + '"]').css("display")=="block"){
				 $(this).parent().find('[data-con="' + level + '"]').hide();
				 $(this).removeClass(options.activeClass);
				  }	
			  else{
				 $(box).find('[data-role="tree_action"]').hide();
				 $(box).find('[data-role="root"]').removeClass(options.activeClass);
				 $(this).parent().find('[data-con="' + level + '"]').show();
				 $(this).addClass(options.activeClass);
				}	 
			});
				
			  if(options.openRow >= 0){
				$(box).find('li:nth-child('+options.openRow+')').find('[data-role="root"]').addClass(options.activeClass);
				$(box).find('li:nth-child('+options.openRow+')').find('[data-role="tree_action"]').show();
			   }
		     });
		
	};

})(jQuery);
var $menu, $body, $pages;

$(document).ready(function() {

	$menu = $(".menu a");
	$body = $("body,html");
	$pages = $(".section > div");

  $menu.click(function(){

		var index = $menu.index(this);
		var offset = $pages.eq(index).offset().top;

		$body.animate({ scrollTop : offset }, 500);

		return false;

	});

	$(document).trigger("scroll");

});

$(document).scroll(function() {

	var index = Math.round($(this).scrollTop()/$(window).height());

	$menu.removeClass("active");
	$menu.eq(index).addClass("active");

});

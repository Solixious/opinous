$(document).on("click", "span.like", function() {
	if($(this).next('.react-icon').hasClass('disliked')) {
		unDislikeFunc($(this).next('.react-icon'));
	} else {
		likeFunc($(this));
	}
});
$(document).on("click", "span.dislike", function() {
	if($(this).prev('.react-icon').hasClass('liked')) {
		unlikeFunc($(this).prev('.react-icon'));
	} else {
		dislikeFunc($(this));
	}
});
$(document).on("click", "span.liked", function() {
    unlikeFunc($(this));
});
$(document).on("click", "span.disliked", function() {
    unDislikeFunc($(this));
});
var likeFunc = function(ele) {
    var id = ele.data('id');
    var likes = parseInt(ele.prev('.react-count').text());
    likes = likes + 1;
    ele.prev('.react-count').html(likes);
    ele.removeClass('like').addClass('liked');
    $.ajax({
        type: 'POST',
        url: '/post/like/' + id
    });
};
var dislikeFunc = function(ele) {
    var id = ele.data('id');
    var dislikes = parseInt(ele.next('.react-count').text());
    dislikes = dislikes + 1;
    ele.next('.react-count').html(dislikes);
    ele.removeClass('dislike').addClass('disliked');
    $.ajax({
        type: 'POST',
        url: '/post/dislike/' + id
    });
}
var unlikeFunc = function(ele){
    var id = ele.data('id');
    var likes = parseInt(ele.prev('.react-count').text());
    likes = likes - 1;
    ele.prev('.react-count').html(likes);
    ele.removeClass('liked').addClass('like');
    $.ajax({
        type: 'POST',
        url: '/post/unlike/' + id
    });
};
var unDislikeFunc = function(ele) {
    var id = ele.data('id');
    var dislikes = parseInt(ele.next('.react-count').text());
    dislikes = dislikes - 1;
    ele.next('.react-count').html(dislikes);
    ele.removeClass('disliked').addClass('dislike');
    $.ajax({
        type: 'POST',
        url: '/post/undislike/' + id
    });
};

var up = "up", down = "down";
var inAnimation = false;
$(".room-title").bind('mouseover', function() {
	var currHeight = $(this).height();
	$(this).css("max-height", "80%");
	$(this).css('height','auto');
	var autoHeight = $(this).height();
	$(this).css('height', currHeight);
    inAnimation = true;
    $(this).stop();
    $(this).animate({
        height: autoHeight,
        duration: 500,
        queue: false
    }, function() {
        inAnimation = false;
    });

	
	var descHeight = $(this).next(".room-description").height() - (autoHeight - currHeight);
    inAnimation = true;
    $(this).next(".room-description").stop();
    $(this).next(".room-description").animate({
        height: descHeight,
        duration: 500,
        queue: false
    }, function() {
        inAnimation = false;
    });

});
$(".room-title").bind('mouseleave', function() {
	var currHeight = $(this).height();
	$(this).css("max-height", "30%");
	$(this).css('height','auto');
	var autoHeight = $(this).height();
	$(this).css("max-height", "80%");
	$(this).css('height', currHeight);

    inAnimation = true;
    $(this).stop();
    $(this).animate({
        height: autoHeight,
        duration: 500,
        queue: false
    }, function() {
        inAnimation = false;
    });


    var descHeight = $(this).next(".room-description").height() + (currHeight - autoHeight);
    inAnimation = true;
    $(this).next(".room-description").stop();
    $(this).next(".room-description").animate({
        height: descHeight,
        duration: 500,
        queue: false
    }, function() {
        inAnimation = false;
    });


});
$(".room-description").bind('mouseover', function() {
	animateContent($(this), $(this).find('.description-content'), down);
	
});
$(".room-description").bind('mouseleave', function() {
	if($(this).height() < $(this).find('.description-content').height()) {
		animateContent($(this), $(this).find('.description-content'), up);
	}
});
function animateContent(ele, ele2, direction) {
    var animationOffset = ele.height() - ele2.height();
    var speed = ele2.height() * 25;
    if (direction == up) {
        animationOffset = 0;
        speed = "fast";
    }
    	inAnimation = true;
    	ele2.stop();
        ele2.animate({
            "marginTop": animationOffset + "px"
        }, speed, "linear", function() {
            inAnimation = false;
        });

}

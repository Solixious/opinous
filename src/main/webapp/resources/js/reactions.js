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
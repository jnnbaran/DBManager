
var shareCount = $(".shareCount");

var count = 0;
var downVote = 0;

function shareCounter() {
    var $t = $(this);
    if ($t.hasClass("plus")) {
        count = count + 1;
        $t
            .parent()
            .find(".share-plus")
            .html(count);
    } else {
        downVote = downVote + 1;
        $t
            .parent()
            .find(".share-minus")
            .html(downVote);
    }
}


shareCount.click(shareCounter);

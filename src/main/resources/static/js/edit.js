function toggle(jobId) {
    var detail = document.getElementById('detail');
    if (detail.style.display === 'none' || detail.style.display === '') {
        detail.style.display = 'block';
    } else {
        detail.style.display = 'none';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    var boxes = document.querySelectorAll('.color-box');
    boxes.forEach(function(box) {
        console.log(box.id);
        if (box.id === 'job') {
            console.log("hi");
            var jobId = box.getAttribute('job-id');
            box.addEventListener('mouseover', function() {
                box.classList.add('hover');
            });
            box.addEventListener('mouseout', function() {
                box.classList.remove('hover');
            });
            box.addEventListener('click', function() {
                if (box.classList.contains('hover')) {
                    toggle(jobId);
                }
            });
        }
    });
});

document.addEventListener('DOMContentLoaded', function() {
    var boxes = document.querySelectorAll('.job');
    boxes.forEach(function(box) {
        var jobId = box.getAttribute('job-id');
        box.addEventListener('mouseover', function() {
            box.classList.add('hover');
        });
        box.addEventListener('mouseout', function() {
            box.classList.remove('hover');
        });
        box.addEventListener('click', function() {
            if (box.classList.contains('hover')) {
                toggle(jobId);
            }
        });
    });
});

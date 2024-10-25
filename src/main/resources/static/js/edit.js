function loadPage(url) {
    window.location.href = url;
}

function toggle(jobId) {
    console.log(jobId);
    var detail = jobId;
    if (detail.style.display === 'none' || detail.style.display === '') {
        detail.style.display = 'block';
    } else {
        detail.style.display = 'none';
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const actions = {
        job: function() {
            const jobDB = document.getElementById('jobDB');
            toggle(jobDB);
        },
        wage: function() {
            const wageDB = document.getElementById('wageDB');
            toggle(wageDB);
        },
        qualification: function() {
            const qualificationDB = document.getElementById('qualificationDB');
            toggle(qualificationDB);
        },
        record: function() {
            const recordDB = document.getElementById('recordDB');
            toggle(recordDB);
        }
    }
    document.getElementById('job').addEventListener('click', actions.job);
    document.getElementById('wage').addEventListener('click', actions.wage);
    document.getElementById('qualification').addEventListener('click', actions.qualification);
    document.getElementById('record').addEventListener('click', actions.record);
});
//const detailData = {
//    job: `
//        <tr th:each="index : ${#numbers.sequence(0, backgroundChecks.size() - 1)}">
//            <div th:if="${beneficiaries[index].getLastName() == beneficiary.getLastName()}">
//                <td th:text="${beneficiaries[index].getLastName()}" th:style="'background-color: #fffed7; color: #000'"></td>
//                <td th:text="${backgroundChecks[index].jobTitleVerification}" th:style="'background-color: #fffed7'"></td>
//                <td th:style="'background-color: #fffed7'">
//                    <div class="color-box" th:style="'background-color: ' + ${colors[index]}"></div>
//                </td>
//            </div>
//            <div th:unless="${beneficiaries[index].getLastName() == beneficiary.getLastName()}">
//                <td th:text="${beneficiaries[index].getLastName()}"></td>
//                <td th:text="${backgroundChecks[index].jobTitleVerification}"></td>
//                <td>
//                    <div class="color-box" th:style="'background-color: ' + ${colors[index]}"></div>
//                </td>
//            </div>
//        </tr>
//    `
//}
//
//document.querySelectorAll('').forEach(box => {
//    box.addEventListener('click', function() {
//        const itemId = this.getAttribute('');
//        const detailsElement = document.getElementById(``);
//        const row = this.closest('tr');
//
//        if (!row.nextElementSibling || !row.nextElementSibling.classList.contains('')) {
//            const newRow = document.createElement('tr');
//            newRow.classList.add('');
//
//        }
//    })
//})

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
        if (box.id === 'job') {
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

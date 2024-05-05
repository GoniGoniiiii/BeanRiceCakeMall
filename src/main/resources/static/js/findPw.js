document.addEventListener("DOMContentLoaded", function() {
    var selectValue = document.getElementById("select-value");
    var tel = document.getElementById("tel");
    var email = document.getElementById("email");

    if(selectValue.value==='email'){
        email.classList.remove("hidden");
        tel.classList.add("hidden");
    }

    selectValue.addEventListener("change", function() {
        var select = selectValue.value;
        console.log("선택된 인증 방법 : " + select);

        if (select === 'tel') {
            email.classList.add("hidden");
            tel.classList.remove("hidden");
        } else {
            email.classList.remove("hidden");
            tel.classList.add("hidden");
        }
    });
});



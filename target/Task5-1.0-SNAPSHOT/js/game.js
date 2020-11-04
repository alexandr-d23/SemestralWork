window.onload
{
    const cube = document.getElementById("cube");
    const body = document.getElementById("right-block");
    const header = document.getElementById("right-block");
    const score = document.getElementById("scoreCount")
    let clickPower = 1
    let circle = document.getElementById("circle")
    let pupil1 = document.getElementById("pupil1");
    let pupil2 = document.getElementById("pupil2");
    let gameblock = document.getElementById('gameblock');
    let width = window.innerWidth
    let height = window.innerHeight
    let colorArray = ['#0468fc', '#611e9d', '#03f85d', '#fa4040', '#f31717', '#fc07ee', '#af0bf6', '#af0bf6', '#0AF3B1', '#878881', '#be5564'];
    let colorIndex = 0
    let followCursor = true
    let countOfClick = 0
    let maxCountOfClick = 20

    function GetScore(context) {
        if (countOfClick == 0 || countOfClick >= maxCountOfClick) {
            if (countOfClick == 0) countOfClick = 1
            ajax(context, countOfClick)
        }
        countOfClick++
        if (countOfClick > maxCountOfClick) countOfClick = 1
        score.innerHTML = (Number(score.innerText) + Number(clickPower)).toString()
        colorIndex++;
        if (colorIndex === colorArray.length) colorIndex = 0
        gameblock.style.background = colorArray[colorIndex]
    }

    function ajax(context, value) {
        $.ajax({
            url: context + "/game?value=" + value,
            type: "PUT",
            dataType: "json",
            success: [function (msg) {
                if (clickPower == 1) {
                    countOfClick += (clickPower - 1)
                }
                clickPower = msg.clickPower
            }
            ]
        });
    }


    function move(myX, myY) {

        let x = Number(myX)
        let y = Number(myY)
        let left = ((width / 2 - x) / (width))
        let top = ((height / 2 - y) / (height))
        // console.log(left+" "+ top)
        let marginLeft = (30 - (left * (Math.abs(width / 2 - x) * 60 / width)) / (Math.sqrt(left * left + top * top))) + 'px'
        let marginTop = (30 - (top * Math.abs(height / 2 - y) * 60 / height) / (Math.sqrt(left * left + top * top))) + 'px'
        pupil1.style.marginLeft = marginLeft
        pupil1.style.marginTop = marginTop
        pupil2.style.marginLeft = marginLeft
        pupil2.style.marginTop = marginTop
        circle.style.background = colorArray[Math.floor(Math.random() * (colorArray.length - 1))]

    }

    gameblock.addEventListener("mousemove", eyeMove);

    function eyeMove(event) {
        if (followCursor) {
            let left = ((width / 2 - event.pageX) / (width))
            let top = ((height / 2 - event.pageY) / (height))
            console.log(left + " " + top)
            let marginLeft = (30 - (left * (Math.abs(width / 2 - event.pageX) * 60 / width)) / (Math.sqrt(left * left + top * top))) + 'px'
            let marginTop = (30 - (top * Math.abs(height / 2 - event.pageY) * 60 / height) / (Math.sqrt(left * left + top * top))) + 'px'
            pupil1.style.marginLeft = marginLeft
            pupil1.style.marginTop = marginTop
            pupil2.style.marginLeft = marginLeft
            pupil2.style.marginTop = marginTop
            circle.style.background = colorArray[Math.floor(Math.random() * (colorArray.length - 1))]
        }
    }
// let header_height = header.style.he
    cube.onclick = function () {
        followCursor = false
        start(true)
    }

    function start(flag) {
        let start = Date.now();
        let timer = setInterval(function () {
            // console.log(cube.offsetTop)
            let timePassed = Date.now() - start;
            console.log(cube.offsetLeft)
            move(cube.offsetLeft, cube.offsetTop)
            if (flag) cube.style.top = timePassed * 2 + 'px';
            else cube.style.top = (body.offsetHeight - timePassed * 2) + 'px';

            if (flag && cube.offsetTop >= body.offsetHeight) {
                clearInterval(timer)
                change(false)
                //  end()
            }
            if (!flag && cube.offsetTop <= 0) {
                clearInterval(timer)
                change(true)
            }

        }, 20);

    }

    function change(flag) {
        start(flag)
    }
}

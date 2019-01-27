function copyToClipboard(e) {
    // コピー対象をJavaScript上で変数として定義する
    var index = e.target.getAttribute("data-index");
    var copyTarget = document.querySelectorAll(".copyTarget")[index];

    // 仮想のdiv要素を作りそこに引数で受けた文字列を持たせる
    var tempElement = document.createElement('div');
    tempElement.appendChild(document.createElement('pre')).textContent = copyTarget.value;

    // 仮想のdiv要素をviewに表示させないようにする
    tempElement.style.position = 'fixed';
    tempElement.style.left = '-100%';

    document.body.appendChild(tempElement);
    document.getSelection().selectAllChildren(tempElement);
    document.execCommand('copy');
    document.body.removeChild(tempElement);

    // コピーをお知らせする
    alert("コピーできました！ : " + copyTarget.value);
}
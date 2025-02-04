document.querySelector("li").style.backgroundColor = "red";

//class를 선택하는 방법
let container = document.querySelector(".container");
let match =container.querySelector("p");

//id를 선택하는 방법
let button = document.querySelector("#myButton");
let menulist = document.querySelector("#itemList");

console.log("Print Container : " + container);
console.log("Print Container.getHTML : " + container.getHTML());
console.log("Print p : " + match);
console.log("Print Match.getHTML : " + match.getHTML());
match.style.color = "darkblue";

button.addEventListener("click", () => {
	alert("버튼이 클릭하였습니다.");
});

const items = ["항목 1", "항목 2", "항목 3"];
for (let i = 0; i < items.length; i++) {
  let listItem = document.createElement("li");
  listItem.textContent = items[i];
  itemList.appendChild(listItem);
}
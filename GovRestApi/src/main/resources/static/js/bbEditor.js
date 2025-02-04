// Initialize the Toast UI Editor
//const Editor = new toastui.Editor;
const bbEditor = new toastui.Editor({
//const editor = new Editor({	
	el: document.querySelector('#bbEditor'),
	height: '500px',
	initialEditType: 'wysiwyg',
	//initialValue: 'Welcome, Toast WebEditor',
	previewStyle: 'vertical',
	// toolbarItems 뻬면 기본적인 툴바 다 사용하는 것
	toolbarItems: [
	    ['heading', 'bold', 'italic', 'strike'],
	  //  ['hr', 'quote'],
	    ['ul', 'ol', 'task', 'indent', 'outdent'],
	    ['table', 'image', 'link'],
	  //  ['code', 'codeblock'],
	    ['scrollSync'],
	  ]
});
// HTML 문자열 생성 
let vDocu = "";
vDocu += "<h3>제목</h3>";
vDocu += "<p>내용</p>";
bbEditor.setHTML(vDocu);
//const content = bbEditor.getHTML();

document.querySelector('#temp-save').addEventListener('click', function() {
	const title = document.querySelector("#bb_title").value;
	const category = document.querySelector("#bb_category").value;
	const tag = document.querySelector("#bb_tag").value;
	const content = bbEditor.getHTML();
	console.log("title : " + title);
	console.log("category : " + category);
	console.log("tag : " + tag);
	console.log("Print Content : " + content);
	
	$.ajax({
		type: 'post', // 요청 방식
        url: '/addBbContent', // 요청 url 주소
        data: {
			bbcustomerID: 1,
            bbTitle: title,
            bbCategory: category,
            bbTag: tag,
			bbContent: content,
			bbImagePath: "c:\\images",
			bbStatus: "F"
        }, // 전달할 데이터 -> 여러 형태가 될 수 있음(json, list 등)
        success: function(data) {
            if (data == 'success') {
                alert('Content 등록되었습니다.');
            }
        }, // ajax 통신 성공 시 실행 로직
        error: function() {
            alert('error');
        } // ajax 통신 실패 시 실행 로직
	});
	
/*	fetch('/generate-doc', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded',
		},
		body: newURLSearchParams({ content: content })
		})
		.then(response => {
			if (response.ok) {
			return response.blob();
			} else {
			throw new Error('Failed to generate DOCX');
			}
		})
		.then(blob => {
			const link = document.createElement('a');
			link.href = URL.createObjectURL(blob);
			link.download = 'generatedDocument.docx';
			link.click();
		})
	.catch(error => console.error('Error:', error));*/
});
// Initialize the Toast UI Editor
//const Editor = new toastui.Editor;
const editor = new toastui.Editor({
//const editor = new Editor({	
	el: document.querySelector('#editor'),
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
editor.setHTML(vDocu);

document.querySelector('#temp-save').addEventListener('click', function() {
	const content = editor.getHTML();
	fetch('/generate-doc', {
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
	.catch(error => console.error('Error:', error));
});
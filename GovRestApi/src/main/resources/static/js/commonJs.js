function submitData() {
    const data = document.getElementById("inputData").value;

    fetch('/submitData', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data) // {  data : "wqwqwqwqw"    }
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById("responseMessage").innerHTML = "<font color='red'>"+data+"</font>";
    })
    .catch(error => console.error('Error:', error));
}

async function submitData2() {
    const data2 = document.getElementById("inputData2").value;

	try {
		const response2 = await fetch('/submitData2', {
		        method: 'POST',
		        headers: {
		            'Content-Type': 'application/json'
		        },
		        body: JSON.stringify(data2) // {  data : "wqwqwqwqw"    }
		    });
		 const responseData = await response2.text();
		 document.getElementById("responseMessage2").innerHTML = responseData;
	} catch(error) {
		console.error('Error:', error);
	}
}
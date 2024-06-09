async function removeCashDesk(id) {
    if (confirm("آیا از حذف صندوق " + id + " اطمینان دارید؟")) {
        const response = await fetch("/api/cashDesk/" + id, {
            method: "DELETE"
        });
        document.location.replace("/cashDesk.do")
    }
}

function showEditCashDesk(id) {
    document.location.replace("/cashDeskEdit.do?id=" + id);
}

function editCashDesk(event) {
    event.preventDefault()
    const cashDeskEditForm = document.getElementById("cashDeskEditForm");
    const formData=new FormData(cashDeskEditForm)
    console.log(formData.get("id"))
    fetch("/cashDeskEdit.do", {
        method: "PUT",
        body:formData
    }).then(() => {
        document.location.replace("/cashDeskDisplay.do?id=" + formData.get("id"));
    });
}

function selectCashDesk(id) {
    document.location.replace("/cashDeskDisplay.do?id=" + id);
}
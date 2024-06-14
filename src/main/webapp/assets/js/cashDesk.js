function showEditCashDesk(id) {
    document.location.replace("/cashDeskEdit.do?id=" + id);
}

async function removeCashDesk(id) {
    if (confirm("آیا از حذف صندوق " + id + " اطمینان دارید؟")) {
        const response = await fetch("/api/cashDesk/" + id, {
            method: "DELETE"
        });
        if (response.ok) {
            alert("صندوق با موفقیت حذف شد");
            document.location.replace("/cashDeskBox.do");
        } else {
            alert("حذف صندوق با خطا مواجه شد");
        }
    }
}

async function editCashDesk(event) {
    event.preventDefault();
    const cashDeskEditForm = document.getElementById("CashDeskEditForm");
    const formData = new FormData(cashDeskEditForm);
    const id = formData.get("id");

    const cashDeskData = {
        id: formData.get("id"),
        name: formData.get("name"),
        cashDeskNumber: formData.get("cashDeskNumber"),
        cashBalance: formData.get("cashBalance"),
        username: formData.get("username"),
    };

    console.log('Cash Desk Data:', cashDeskData); // برای دیباگ

    const response = await fetch("/api/cashDesk/edit", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(cashDeskData)
    });

    if (response.ok) {
        alert("تغییرات با موفقیت ثبت شد");
        document.location.replace("/cashDeskDisplay.do?id=" + id);
    } else {
        console.error("Failed to update the cash desk");
        alert("خطایی رخ داد. لطفاً دوباره امتحان کنید.");
    }
}

function selectCashDesk(id) {
    document.location.replace("/cashDeskDisplay.do?id=" + id);
}
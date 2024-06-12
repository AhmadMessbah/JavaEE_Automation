function showEditCashDesk(id) {
    document.location.replace("/cashDeskEdit.do?id=" + id);
}

async function removeCashDesk(id) {
    if (confirm("آیا از حذف صندوق " + id + " اطمینان دارید؟")) {
        try {
            const response = await fetch("/api/cashDesk/" + id, {
                method: "DELETE"
            });
            if (response.ok) {
                alert("صندوق با موفقیت حذف شد");
                document.location.replace("/cashDeskBox.do");
            } else {
                const errorData = await response.json();
                alert("حذف صندوق با خطا مواجه شد: " + (errorData.message || "خطای ناشناخته"));
            }
        } catch (error) {
            console.error("Error deleting cash desk:", error);
            alert("خطایی رخ داد. لطفاً دوباره امتحان کنید.");
        }
    }
}

async function editCashDesk(event) {
    event.preventDefault();
    const cashDeskEditForm = document.getElementById("cashDeskEditForm");
    const formData = new FormData(cashDeskEditForm);
    const id = formData.get("id");

    const cashDeskData = {
        id: formData.get("id"),
        name: formData.get("name"),
        cashDeskNumber: formData.get("cashDeskNumber"),
        cashBalance: formData.get("cashBalance"),
        user: formData.get("user"),
    };

    console.log('Cash Desk Data:', cashDeskData); // برای دیباگ

    try {
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
            const errorData = await response.json();
            alert("ویرایش صندوق با خطا مواجه شد: " + (errorData.message || "خطای ناشناخته"));
        }
    } catch (error) {
        console.error("Error editing cash desk:", error);
        alert("خطایی رخ داد. لطفاً دوباره امتحان کنید.");
    }
}

function selectCashDesk(id) {
    document.location.replace("/cashDeskDisplay.do?id=" + id);
}
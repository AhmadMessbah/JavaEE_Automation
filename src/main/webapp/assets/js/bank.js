function showEditBank(id) {
    document.location.replace("/bankEdit.do?id=" + id);
}

async function removeBank(id) {
    if (confirm("آیا از حذف بانک " + id + " اطمینان دارید؟")) {
        const response = await fetch("/api/bank/" + id, {
            method: "DELETE"
        });
        if (response.ok) {
            alert("بانک با موفقیت حذف شد");
            document.location.replace("/bankBox.do");
        } else {
            alert("حذف بانک با خطا مواجه شد");
        }
    }
}

async function editBank(event) {
    event.preventDefault();
    const bankEditForm = document.getElementById("bankEditForm");
    const formData = new FormData(bankEditForm);
    const id = formData.get("id");

    const bankData = {
        id: formData.get("id"),
        name: formData.get("name"),
        accountNumber: formData.get("accountNumber"),
        branchCode: formData.get("branchCode"),
        branchName: formData.get("branchName"),
        accountType: formData.get("accountType"),
        accountBalance: formData.get("accountBalance")
    };

    console.log('Bank Data:', bankData); // برای دیباگ

    const response = await fetch("/api/bank/edit", {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(bankData)
    });

    if (response.ok) {
        alert("تغییرات با موفقیت ثبت شد");
        document.location.replace("/bankDisplay.do?id=" + id);
    } else {
        console.error("Failed to update the bank");
        alert("خطایی رخ داد. لطفاً دوباره امتحان کنید.");
    }
}

function selectBank(id) {
    document.location.replace("/bankDisplay.do?id=" + id);
}
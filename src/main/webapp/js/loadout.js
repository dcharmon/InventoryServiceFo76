
/**
 * Recalculates and updates the resistance totals row in the loadout summary table
 * by summing the resistance values of all currently selected armor pieces.
 */
function updateTotals() {
    const totals = {dr: 0, er: 0, rr: 0, pr: 0, fr: 0, cr: 0};
    document.querySelectorAll('.armor-radio:checked').forEach(function(radio) {
        const res = resistanceData[radio.value];
        if (res) {
            totals.dr += res.dr;
            totals.er += res.er;
            totals.rr += res.rr;
            totals.pr += res.pr;
            totals.fr += res.fr;
            totals.cr += res.cr;
        }
    });
    document.getElementById('totalDr').textContent = totals.dr;
    document.getElementById('totalEr').textContent = totals.er;
    document.getElementById('totalRr').textContent = totals.rr;
    document.getElementById('totalPr').textContent = totals.pr;
    document.getElementById('totalFr').textContent = totals.fr;
    document.getElementById('totalCr').textContent = totals.cr;
}

/**
 * Updates the loadout summary row for a given slot with the selected piece's
 * armor type name, legendary effects, and resistance values.
 *
 * @param {string} figId - The slot identifier (e.g. 'left-arm', 'torso').
 * @param {HTMLElement|null} radio - The selected radio button element, or null to clear the row.
 */
function updateSummary(figId, radio) {
    const row = document.getElementById('summary-' + figId);
    if (!row) return;
    const pieceId = radio ? radio.value : null;
    const res = pieceId ? resistanceData[pieceId] : null;

    if (pieceId && resistanceData[pieceId]) {
        row.querySelector('[data-col="name"]').textContent = resistanceData[pieceId].name;
        row.querySelector('[data-col="name"]').className = '';
        row.querySelector('[data-col="star1"]').textContent = radio.getAttribute('data-star1') || '--';
        row.querySelector('[data-col="star2"]').textContent = radio.getAttribute('data-star2') || '--';
        row.querySelector('[data-col="star3"]').textContent = radio.getAttribute('data-star3') || '--';
        row.querySelector('[data-col="star4"]').textContent = radio.getAttribute('data-star4') || '--';
        row.querySelector('[data-col="dr"]').textContent = res ? res.dr : '--';
        row.querySelector('[data-col="er"]').textContent = res ? res.er : '--';
        row.querySelector('[data-col="rr"]').textContent = res ? res.rr : '--';
        row.querySelector('[data-col="pr"]').textContent = res ? res.pr : '--';
        row.querySelector('[data-col="fr"]').textContent = res ? res.fr : '--';
        row.querySelector('[data-col="cr"]').textContent = res ? res.cr : '--';
    } else {
        row.querySelectorAll('[data-col]').forEach(function(td) {
            td.textContent = td.dataset.col === 'name' ? '\u2014' : '';
            td.className = td.dataset.col === 'name' ? 'text-muted' : '';
        });
    }
}

/**
 * Shows the standard armor or power armor section based on the currently
 * selected loadout type radio button.
 */
function setLoadoutType(type) {
    document.getElementById('loadoutType').value = type;
    document.getElementById('standardSection').style.display   = type === 'STANDARD'    ? 'block' : 'none';
    document.getElementById('powerArmorSection').style.display = type === 'POWER_ARMOR' ? 'block' : 'none';
    document.getElementById('btnStandard').classList.toggle('active', type === 'STANDARD');
    document.getElementById('btnPowerArmor').classList.toggle('active', type === 'POWER_ARMOR');
}

$(document).ready(function() {

    // Attach change listeners to armor slot radios
    document.querySelectorAll('.armor-radio').forEach(function(radio) {
        radio.addEventListener('change', function() {
            const figId = this.getAttribute('data-fig');
            const hiddenMap = {
                'left-arm': 'hidden-left-arm',
                'right-arm': 'hidden-right-arm',
                'torso': 'hidden-torso',
                'left-leg': 'hidden-left-leg',
                'right-leg': 'hidden-right-leg'
            };
            document.getElementById(hiddenMap[figId]).value = this.value;
            updateSummary(figId, this);
            updateTotals();
        });
    });

    // Disable empty hidden inputs before form submit
    document.querySelector('form').addEventListener('submit', function() {
        document.querySelectorAll('input[type="hidden"][name="armorPieceIds"]').forEach(function(el) {
            if (!el.value) el.disabled = true;
        });
    });

    // Restore summary and totals for any pre-selected pieces (edit loadout)
    document.querySelectorAll('.armor-radio:checked').forEach(function(radio) {
        const figId = radio.getAttribute('data-fig');
        if (figId && radio.value) {
            document.getElementById('hidden-' + figId).value = radio.value;
            updateSummary(figId, radio);
        }
    });
    updateTotals();

    setLoadoutType('STANDARD');
});
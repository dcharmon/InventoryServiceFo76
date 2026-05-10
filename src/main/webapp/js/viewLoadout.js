/**
 * Filters visible loadout panels by type and updates the active filter button.
 *
 * @param {string} type - 'all', 'STANDARD', or 'POWER_ARMOR'
 */
function filterLoadouts(type) {
    const panels = document.querySelectorAll('.loadout-panel');
    let visible = 0;

    panels.forEach(function(panel) {
        const show = type === 'all' || panel.getAttribute('data-type') === type;
        panel.style.display = show ? 'block' : 'none';
        if (show) visible++;
    });

    document.getElementById('noResults').style.display = visible === 0 ? 'block' : 'none';

    document.getElementById('filterAll').classList.toggle('active', type === 'all');
    document.getElementById('filterStandard').classList.toggle('active', type === 'STANDARD');
    document.getElementById('filterPa').classList.toggle('active', type === 'POWER_ARMOR');
}
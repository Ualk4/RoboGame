load(path + 'outerFileforCalculation.js')
x = currentCharacter.getCords().getX()
y = currentCharacter.getCords().getY()

if(currentCharacter.getItem() != null){
    if(currentCharacter.getItem().getHealth() == 0){
        singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1);
    } else
    singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1)
} else if (currentCharacter.getItem() == null) {
    singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1)
}
if (singlePath == null) {
    singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1)
}
if (singlePath != null) {
    moveToClosestTile_method(currentCharacter)
}
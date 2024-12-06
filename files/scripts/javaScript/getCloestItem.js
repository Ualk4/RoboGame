x = currentCharacter.getCords().getX();
y = currentCharacter.getCords().getY();

if(currentCharacter.getItem() != null){
    if(currentCharacter.getItem().getHealth() == 0){
        singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1);
    } else
    singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1);
} else if (currentCharacter.getItem() == null) {
    singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1);
}
if (singlePath == null) {
    singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1);
}
if (singlePath != null) {
    if ((singlePath.getLength() == 1)) {
        if((currentCharacter.getTile().isKitchen()) || (currentCharacter.getTile().isTrashCan())){
            currentCharacter.putDown();
        } else {
            currentCharacter.pickUp();
        }
    } else if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() - 1) == y)) {
        currentCharacter.moveDown();
    } else if ((singlePath.getFirstTile().getCords().getX() == x) && ((singlePath.getFirstTile().getCords().getY() + 1) == y)) {
        currentCharacter.moveUp();
    } else if (((singlePath.getFirstTile().getCords().getX() + 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
        currentCharacter.moveLeft();
    } else if (((singlePath.getFirstTile().getCords().getX() - 1) == x) && (singlePath.getFirstTile().getCords().getY() == y)) {
        currentCharacter.moveRight();
    }
}
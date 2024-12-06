x = currentCharacter.getCords().getX()
y = currentCharacter.getCords().getY()
others_Aim = []
singlePath = None

if isThereSavable:
    if  currentCharacter.getClosestItemTile() is not None:
        if len(characterArray) > 1:
            if currentCharacter.getAim() is None:
                singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1)
                for character in characterArray:
                    if character != currentCharacter and character.getAim() is not None and singlePath is not None:
                        if character.getAim() == singlePath.getLastTile():
                            singlePath = None

                if currentCharacter.getClosestItemTileWithoutArray(others_Aim) is not None:
                    singlePath = AStar.search(currentCharacter.getClosestItemTileWithoutArray(others_Aim), currentCharacter.getTile(), -1)
                

    
    if singlePath is None:
        singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1)
   
    if currentCharacter.getItem() is not None:
        if currentCharacter.getItem().getHealth() is None or currentCharacter.getItem().getHealth() > 0:
            singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1)
        else:
            singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1)
        

    if singlePath is not None:
        currentCharacter.setAim(singlePath.getLastTile())
        if singlePath.getLength() == 1:
            if currentCharacter.getItem() is not None:
                if currentCharacter.getTile(). isKitchen() or currentCharacter.getTile(). isTrashCan():
                    currentCharacter.putDown()
                    currentCharacter.setAim(null)
            else:
                currentCharacter.pickUp()
        elif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() - 1 == y:
            currentCharacter.moveDown()
        elif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() + 1 == y:
            currentCharacter.moveUp()
        elif singlePath.getFirstTile().getCords().getX() + 1 == x and singlePath.getFirstTile().getCords().getY() == y:
            currentCharacter.moveLeft()
        elif singlePath.getFirstTile().getCords().getX() - 1 == x and singlePath.getFirstTile().getCords().getY() == y:
            currentCharacter.moveRight()
x = currentCharacter.getCords().getX()
y = currentCharacter.getCords().getY()

if !currentCharacter.getItem().nil?
    if currentCharacter.getItem().getHealth() == 0
          singlePath = AStar.search(AStar.trashCan(), currentCharacter.getTile(), -1)
    else
      singlePath = AStar.search(currentCharacter.getClosestKitchenEntrance(), currentCharacter.getTile(), -1)
    end
elsif currentCharacter.getItem().nil?
    singlePath = AStar.search(currentCharacter.getClosestItemTile(), currentCharacter.getTile(), -1)
end
if singlePath.nil?
    singlePath = AStar.search(currentCharacter.getClosestUnknownTile(), currentCharacter.getTile(), -1)
end
if !singlePath.nil?
    if singlePath.getLength() == 1
        if currentCharacter.getTile().isKitchen() or currentCharacter.getTile().isTrashCan()
            currentCharacter.putDown()
        else
            currentCharacter.pickUp()
        end
    elsif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() - 1 == y
        currentCharacter.moveDown()
    elsif singlePath.getFirstTile().getCords().getX() == x and singlePath.getFirstTile().getCords().getY() + 1 == y
        currentCharacter.moveUp()
    elsif singlePath.getFirstTile().getCords().getX() + 1 == x and singlePath.getFirstTile().getCords().getY() == y
        currentCharacter.moveLeft()
    elsif singlePath.getFirstTile().getCords().getX() - 1 == x and singlePath.getFirstTile().getCords().getY() == y
        currentCharacter.moveRight()
    end
end
require File.expand_path( path + 'outerFileforCalculation.rb', __FILE__)

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
    moveToClosestTile_method(currentCharacter, singlePath, x, y)
end
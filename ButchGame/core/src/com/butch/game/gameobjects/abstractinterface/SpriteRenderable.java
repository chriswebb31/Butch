package com.butch.game.gameobjects.abstractinterface;

public abstract class SpriteRenderable {
//    public static ArrayList<SpriteRenderable> spriteRenderables; //want to sort by y-index for correct stacking
//    public Sprite sprite;
//    public Rectangle collider;
//    public Vector2 position;
//
//    public SpriteRenderable(){
//        try{
//            if(spriteRenderables == null)
//                spriteRenderables = new ArrayList<SpriteRenderable>();
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
//        spriteRenderables.add(this);
//    }
//
//    public abstract void update(float delta);
//
//    public void render(SpriteBatch batch){
//        this.sprite.draw(batch);
//    }
//
//    public static void Render(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer, float delta){
//        //TRYING TO AVOID CONCURRENTMODIFICATIONEXCEPTION BY USING FIXED LENGTH FOR LOOP
//        spriteBatch.begin();
//        for(int i=0; i <spriteRenderables.size();i++){
//            spriteRenderables.get(i).update(delta);
//            spriteRenderables.get(i).render(spriteBatch);
//        }
//        if(!(NewBullet.newBullets ==null)) {
//            for (int i = 0; i < NewBullet.newBullets.size(); i++) {
//                NewBullet.newBullets.get(i).update(delta);
//                NewBullet.newBullets.get(i).sprite.draw(spriteBatch);
//            }
//        }
//        spriteBatch.end();
//
//        //sort then render
////        spriteBatch.begin();
////        for (SpriteRenderable spriteRenderable : spriteRenderables) {
////            spriteRenderable.update(delta);
////            System.out.println("delta: "+delta);
////            spriteRenderable.sprite.draw(spriteBatch);
////        }
////        spriteBatch.end();
////
////        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
////        shapeRenderer.setColor(Color.RED);
////        try {
////            for (SpriteRenderable spriteRenderable : spriteRenderables) {
////                shapeRenderer.rect(spriteRenderable.collider.x, spriteRenderable.collider.y, spriteRenderable.collider.width, spriteRenderable.collider.height);
////            }
////        }catch (NullPointerException e){
////                e.printStackTrace();
////            }
////        shapeRenderer.end();
//    }

}

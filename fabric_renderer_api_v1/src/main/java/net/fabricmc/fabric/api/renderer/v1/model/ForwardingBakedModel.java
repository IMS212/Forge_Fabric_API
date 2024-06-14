/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.api.renderer.v1.model;

import java.util.List;
import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;

import net.neoforged.neoforge.client.ChunkRenderTypeSet;
import net.neoforged.neoforge.client.model.data.ModelData;
import net.neoforged.neoforge.common.util.TriState;

/**
 * Base class for specialized model implementations that need to wrap other baked models.
 * Avoids boilerplate code for pass-through methods.
 */
public abstract class ForwardingBakedModel implements BakedModel, WrapperBakedModel {
	/** implementations must set this somehow. */
	protected BakedModel wrapped;

	@Override
	public boolean isVanillaAdapter() {
		return wrapped.isVanillaAdapter();
	}

	@Override
	public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
		wrapped.emitBlockQuads(blockView, state, pos, randomSupplier, context);
	}

	@Override
	public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
		wrapped.emitItemQuads(stack, randomSupplier, context);
	}

	@Override
	public List<BakedQuad> getQuads(BlockState blockState, Direction face, Random rand) {
		return wrapped.getQuads(blockState, face, rand);
	}


	@Override
	public List<BakedQuad> getQuads(BlockState blockState, Direction face, Random rand, ModelData modelData, RenderLayer renderLayer) {
		return wrapped.getQuads(blockState, face, rand, modelData, renderLayer);
	}

	@Override
	public boolean useAmbientOcclusion() {
		return wrapped.useAmbientOcclusion();
	}

	@Override
	public boolean hasDepth() {
		return wrapped.hasDepth();
	}

	@Override
	public boolean isBuiltin() {
		return wrapped.isBuiltin();
	}

	@Override
	public Sprite getParticleSprite() {
		return wrapped.getParticleSprite();
	}

	@Override
	public boolean isSideLit() {
		return wrapped.isSideLit();
	}

	@Override
	public ModelTransformation getTransformation() {
		return wrapped.getTransformation();
	}

	@Override
	public ModelOverrideList getOverrides() {
		return wrapped.getOverrides();
	}

	@Override
	public BakedModel getWrappedModel() {
		return wrapped;
	}

	@Override
	public List<BakedModel> getRenderPasses(ItemStack itemStack, boolean fabulous) {
		return wrapped.getRenderPasses(itemStack, fabulous);
	}

	@Override
	public List<RenderLayer> getRenderTypes(ItemStack itemStack, boolean fabulous) {
		return wrapped.getRenderTypes(itemStack, fabulous);
	}

	@Override
	public ChunkRenderTypeSet getRenderTypes(BlockState state, Random rand, ModelData data) {
		return wrapped.getRenderTypes(state, rand, data);
	}

	@Override
	public Sprite getParticleIcon(ModelData data) {
		return wrapped.getParticleIcon(data);
	}

	@Override
	public ModelData getModelData(BlockRenderView level, BlockPos pos, BlockState state, ModelData modelData) {
		return wrapped.getModelData(level, pos, state, modelData);
	}

	@Override
	public BakedModel applyTransform(ModelTransformationMode transformType, MatrixStack poseStack, boolean applyLeftHandTransform) {
		return wrapped.applyTransform(transformType, poseStack, applyLeftHandTransform);
	}

	@Override
	public TriState useAmbientOcclusion(BlockState state, ModelData data, RenderLayer renderType) {
		return wrapped.useAmbientOcclusion(state, data, renderType);
	}
}
